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

    //TODO : Exception추가하기
    private final DataService dataService;
    private final ProgramOrganozationService programOrganozationService;
    private final CastService castService;
    private final ProgramService programService;

    @GetMapping(value = "/kdrama")
    public ResponseEntity<Object> kdramaData() {
        Message message = dataService.getkdramaData();
        return ResponseEntity.ok()
                .body(message);
    }

    @GetMapping(value = "/kpop")
    public ResponseEntity<Object> kpopData() {
        Message message = dataService.getkpopData();
        return ResponseEntity.ok()
                .body(message);
    }

    @GetMapping(value = "/programorganization")
    public ResponseEntity<Object> programorganization() throws IOException {
        //하루 전 데이터 삭제
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd.");
        String yesterDay = today.minusDays(1).format(dateTimeFormatter);
        programOrganozationService.deleteByBroadcastingDayStartingWith(yesterDay);

        List<Program> programList = programService.findAll();
        for (Program program : programList) {
            Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + program.getTitle() + "방송시간");
            Document document = connection.get();
            List<ProgramOrganization> programOrganizationList = programOrganozationService.findByProgramId(program.getId());

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
                                        .broadcastingDay(broadcastingDay.get(d).text())
                                        .broadcastingTime(broadcastingInfo.select(".time").text())
                                        .broadcastingEpisode(broadcastingInfo.select(".number_text").text())
                                        .broadcastingAge(broadcastingInfo.select(".age_limit").text())
                                        .broadcastingRerun(broadcastingInfo.select(".blind").text())
                                        .broadcastingStation(channel.get(c).text())
                                        .build();
//                          programOrganozationService.save(programOrganization);
//                          System.out.println(programOrganization);
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
                                .broadcastingDay(broadcastingDayRow.get(d).select(".cm_date").text())
                                .broadcastingTime(broadcastingInfo.select(".time").text())
                                .broadcastingEpisode(broadcastingInfo.select(".number_text").text())
                                .broadcastingAge(broadcastingInfo.select(".age_limit").text())
                                .broadcastingRerun(broadcastingInfo.select(".blind").text())
                                .broadcastingStation(broadcastingInfo.select("a").text())
                                .build();
//                      programOrganozationService.save(programOrganization);
//                      System.out.println(programOrganization);
                    }
                }
            }
        }
        return ResponseEntity.ok()
                .body(new Message("succeeded"));
    }

    @GetMapping(value = "/cast")
    public ResponseEntity<Object> cast() throws IOException {
        //TODO : 프로그램 조회 후 크롤링
        //TODO : 신규 프로그램 필터링 해서 업데이트
        //TODO : 테스트용 코드, 스케줄러로 이동 예정
        List<Program> programList = programService.findAll();
        for (Program program : programList) {
            Optional<Cast> castList = castService.findById(program.getId());
            if (!castList.isPresent()) {
                Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + program.getTitle() + "출연진");
                Document document = connection.get();

                Elements castInfos = document.select(".list_image_info .item");
                for (Element castInfo : castInfos) {
                    String imgUrl = castInfo.select("img").attr("abs:src");
                    String playName = castInfo.select(".title_box .name a").text();
                    String actorName = castInfo.select(".title_box .sub_text").text();
                    Cast cast = Cast.builder()
                            .actorName(actorName)
                            .playName(playName)
                            .imgUrl(imgUrl)
                            .build();
                    castService.save(cast);
                }
            }
        }
        return ResponseEntity.ok()
                .body(new Message("succeeded"));
    }

}