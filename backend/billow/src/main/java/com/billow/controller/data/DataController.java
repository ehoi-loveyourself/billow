package com.billow.controller.data;

import com.billow.domain.entity.organization.ProgramOrganization;
import com.billow.domain.entity.program.Cast;
import com.billow.domain.entity.program.Program;
import com.billow.exception.NotFoundException;
import com.billow.model.service.data.DataService;
import com.billow.model.service.organization.ProgramOrganozationService;
import com.billow.model.service.program.CastService;
import com.billow.model.service.program.ProgramService;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/data")
public class DataController {

    private static final String PROGRAM_NOT_FOUND = "프로그램이 존재하지 않습니다.";

    private final DataService dataService;
    private final ProgramOrganozationService programOrganozationService;
    private final CastService castService;
    private final ProgramService programService;

    @GetMapping(value = "/kdrama")
    public ResponseEntity<Object> kdramaData() {
        log.info("kdrama 데이터 수집 API 호출");
        Message message = dataService.getkdramaData();
        log.info("kdrama 데이터 수집 API 성공");
        return ResponseEntity.ok()
                .body(message);
    }

    @GetMapping(value = "/kpop")
    public ResponseEntity<Object> kpopData() {
        log.info("kpop 데이터 수집 API 호출");
        Message message = dataService.getkpopData();
        log.info("kpop 데이터 수집 API 성공");
        return ResponseEntity.ok()
                .body(message);
    }

    @GetMapping(value = "/insert")
    public ResponseEntity<Object> insertProgramId() {
        log.info("성연령별 데이터 프로그램 매핑 API 호출");
        Message message = dataService.insertProgramId();
        log.info("성연령별 데이터 프로그램 매핑 API 성공");
        return ResponseEntity.ok()
                .body(message);
    }

    @GetMapping(value = "/programorganization")
    public ResponseEntity<Object> programorganization() throws IOException {
        log.info("프로그램 편성표 데이터 수집 Scheduler 호출");
        //하루 전 데이터 삭제
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd.");
        String yesterDay = today.minusDays(1).format(dateTimeFormatter);
        programOrganozationService.deleteByBroadcastingDayStartingWith(yesterDay);

        List<Program> programList = programService.findAll();
        for (int i = 0; i < 5; i++) {
//        for (Program program : programList) {
            Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + programList.get(i).getTitle() + "방송시간");
            Document document = connection.get();
            List<ProgramOrganization> programOrganizationList = programOrganozationService.findByProgram_Id(programList.get(i).getId());

            Elements channel = document.select(".table_scroll_wrap>.table_top_area>.cm_table tr a");
            if (!channel.isEmpty()) {
                Elements broadcastingDay = document.select(".table_fixed_wrap span");
                Elements broadcastingDayRow = document.select(".table_scroll_wrap>.table_body_area>.cm_table tr"); //이 사이즈는 날짜 index와 매칭

                for (int d = 0; d < broadcastingDayRow.size(); d++) {//d는 날짜 인덱스와 같음
                    if (!programOrganizationList.isEmpty() && !broadcastingDay.get(d).text().substring(0, 6).equals(today.plusDays(6).format(dateTimeFormatter))) {
                        continue;
                    }
                    Elements broadcastingTimeRow = broadcastingDayRow.get(d).select(">td");
                    for (int c = 0; c < broadcastingTimeRow.size(); c++) {//c는 채널 인덱스와 같음
                        Elements broadcastingInfos = broadcastingTimeRow.get(c).select(".info");
                        if (!broadcastingInfos.isEmpty()) {
                            for (Element broadcastingInfo : broadcastingInfos) {
                                ProgramOrganization programOrganization = ProgramOrganization.builder()
                                        .program(programList.get(i))
                                        .broadcastingDay(broadcastingDay.get(d).text())
                                        .broadcastingTime(broadcastingInfo.select(".time").text())
                                        .broadcastingEpisode(broadcastingInfo.select(".number_text").text())
                                        .broadcastingAge(broadcastingInfo.select(".age_limit").text())
                                        .broadcastingRerun(broadcastingInfo.select(".blind").text())
                                        .broadcastingStation(channel.get(c).text())
                                        .build();
                                programOrganozationService.save(programOrganization);
                            }
                        }
                    }
                }
            } else {
                Elements broadcastingDayRow = document.select(".tvtime_list .info_list");
                for (int d = 0; d < broadcastingDayRow.size(); d++) {
                    Elements broadcastingInfos = broadcastingDayRow.get(d).select(".info");
                    String broadcastingDay = broadcastingDayRow.get(d).select(".cm_date").text();
                    if (!programOrganizationList.isEmpty() && !broadcastingDay.substring(0, 6).equals(today.plusDays(6).format(dateTimeFormatter))) {
                        continue;
                    }
                    for (Element broadcastingInfo : broadcastingInfos) {
                        ProgramOrganization programOrganization = ProgramOrganization.builder()
                                .program(programList.get(i))
                                .broadcastingDay(broadcastingDayRow.get(d).select(".cm_date").text())
                                .broadcastingTime(broadcastingInfo.select(".time").text())
                                .broadcastingEpisode(broadcastingInfo.select(".number_text").text())
                                .broadcastingAge(broadcastingInfo.select(".age_limit").text())
                                .broadcastingRerun(broadcastingInfo.select(".blind").text())
                                .broadcastingStation(broadcastingInfo.select("a").text())
                                .build();
                        programOrganozationService.save(programOrganization);
                    }
                }
            }
        }
        log.info("프로그램 편성표 데이터 수집 Scheduer 성공");
        return ResponseEntity.ok()
                .body(new Message("succeeded"));
    }

    @GetMapping(value = "/cast")
    public ResponseEntity<Object> cast() throws IOException {
        log.info("출연진 데이터 수집 Scheduler 실행");
        List<Program> programList = programService.findAll();
        for (int i = 0; i < 5; i++) {
//            for (Program program : programList) {
            Optional<List<Cast>> castList = castService.findByProgram_Id(programList.get(i).getId());
            if (!castList.isPresent()) {
                Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + programList.get(i).getTitle() + "출연진");
                Document document = connection.get();

                Elements castInfos = document.select(".list_image_info .item");
                for (Element castInfo : castInfos) {
                    String imgUrl = castInfo.select("img").attr("abs:src");
                    String playName = castInfo.select(".title_box .name a").text();
                    String actorName = castInfo.select(".title_box .sub_text").text();
                    Cast cast = Cast.builder()
                            .program(programList.get(i))
                            .actorName(actorName)
                            .playName(playName)
                            .imgUrl(imgUrl)
                            .build();
                    castService.save(cast);
                }
            }
        }
        log.info("출연진 데이터 수집 Scheduler 성공");
        return ResponseEntity.ok()
                .body(new Message("succeeded"));
    }

    @GetMapping(value = "/programdetail")
    public ResponseEntity<Object> programDetail() throws IOException {
        log.info("프로그램 방영정보 데이터 수집 Scheduer 호출");
        //TODO: 엔티티 정리 후에 확인 필요
        List<Program> programList = programService.findAll();
        for (int i = 0; i < 2; i++) {
//        for (Program program : programList){
            Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + programList.get(i).getTitle());
            Document document = connection.get();

            Elements subTitle = document.select(".sub_title span");
            String age = subTitle.get(2).text();
            programList.get(i).setAge(age);

            Elements info = document.select(".info_group");
            if (subTitle.size() == 3) {
                programList.get(i).setEndFlag(true);
                Elements infoDetail = info.get(0).select("dd span");
                String day = infoDetail.get(1).text();
                programList.get(i).setBroadcastingDay(day);
            } else {
                String episode = info.get(0).select("dd .state").text();
                programList.get(i).setBroadcastingEpisode(episode);
            }
            programService.save(programList.get(i));
        }
        log.info("프로그램 방영정보 데이터 수집 Scheduer 성공");
        return ResponseEntity.ok()
                .body(new Message("succeeded"));
    }
}