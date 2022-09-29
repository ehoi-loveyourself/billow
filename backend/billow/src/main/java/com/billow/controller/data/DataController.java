package com.billow.controller.data;

import com.billow.domain.entity.organization.ProgramOrganization;
import com.billow.domain.entity.program.Cast;
import com.billow.domain.entity.program.Program;
import com.billow.model.service.data.DataService;
import com.billow.model.service.organization.ProgramOrganozationService;
import com.billow.model.service.program.CastService;
import com.billow.model.service.program.ProgramService;
import com.billow.util.DateUtil;
import com.billow.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Api(tags = {"Data API"})
@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;
    private final ProgramOrganozationService programOrganozationService;
    private final CastService castService;
    private final ProgramService programService;

    @ApiOperation(value = "kdrama 데이터 수집", response = Object.class)
    @GetMapping(value = "/kdrama")
    public ResponseEntity<Object> kdramaData() {
        log.info("kdrama 데이터 수집 API 호출");
        Message message = dataService.getkdramaData();
        log.info("kdrama 데이터 수집 API 성공");
        return ResponseEntity.ok()
                .body(message);
    }

    @ApiOperation(value = "kpop 데이터 수집", response = Object.class)
    @GetMapping(value = "/kpop")
    public ResponseEntity<Object> kpopData() {
        log.info("kpop 데이터 수집 API 호출");
        Message message = dataService.getkpopData();
        log.info("kpop 데이터 수집 API 성공");
        return ResponseEntity.ok()
                .body(message);
    }

    @ApiOperation(value = "성연령별 데이터 프로그램 매핑", response = Object.class)
    @GetMapping(value = "/insert")
    public ResponseEntity<Object> insertProgramId() {
        log.info("성연령별 데이터 프로그램 매핑 API 호출");
        Message message = dataService.insertProgramId();
        log.info("성연령별 데이터 프로그램 매핑 API 성공");
        return ResponseEntity.ok()
                .body(message);
    }

    @ApiOperation(value = "프로그램 편성표 데이터 수집", response = Object.class)
    @GetMapping(value = "/programorganization")
    public ResponseEntity<Object> programorganization() throws IOException, ParseException {
        log.info("프로그램 편성표 데이터 수집 Scheduler 호출");
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd.");
        Date date = new Date();
        programOrganozationService.deleteByBroadcastingTimeBefore(date);

        List<Program> programList = programService.findAll();
        for (int i = 0; i < programList.size(); i++) {
            Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + programList.get(i).getTitle() + "방송시간");
            Document document = connection.get();
            List<ProgramOrganization> programOrganizationList = programOrganozationService.findByProgram_Id(programList.get(i).getId());

            Elements channel = document.select(".table_scroll_wrap>.table_top_area>.cm_table tr a");
            if (!channel.isEmpty()) {
                Elements broadcastingDay = document.select(".table_fixed_wrap span");
                Elements broadcastingDayRow = document.select(".table_scroll_wrap>.table_body_area>.cm_table tr"); //이 사이즈는 날짜 index와 매칭

                for (int d = 0; d < broadcastingDayRow.size(); d++) {
                    if (!programOrganizationList.isEmpty() && !broadcastingDay.get(d).text().substring(0, 6).equals(today.plusDays(6).format(dateTimeFormatter))) {
                        continue;
                    }
                    Elements broadcastingTimeRow = broadcastingDayRow.get(d).select(">td");
                    for (int c = 0; c < broadcastingTimeRow.size(); c++) {
                        Elements broadcastingInfos = broadcastingTimeRow.get(c).select(".info");
                        if (!broadcastingInfos.isEmpty()) {
                            for (Element broadcastingInfo : broadcastingInfos) {
                                String time = Calendar.getInstance().get(Calendar.YEAR) + "." + broadcastingDay.get(d).text().substring(0, 5) + " " + broadcastingInfo.select(".time").text();
                                String day = broadcastingDay.get(d).text().substring(7);
                                ProgramOrganization programOrganization = ProgramOrganization.builder()
                                        .program(programList.get(i))
                                        .broadcastingDay(day)
                                        .broadcastingTime(DateUtil.toDate(time))
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
                        String time = Calendar.getInstance().get(Calendar.YEAR) + "." + broadcastingDay.substring(0, 5) + " " + broadcastingInfo.select(".time").text();
                        String day = broadcastingDay.substring(7);
                        ProgramOrganization programOrganization = ProgramOrganization.builder()
                                .program(programList.get(i))
                                .broadcastingDay(day)
                                .broadcastingTime(DateUtil.toDate(time))
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
        log.info("프로그램 편성표 데이터 수집 Scheduler 성공");
        return ResponseEntity.ok()
                .body(new Message("succeeded"));
    }

    @ApiOperation(value = "출연진 데이터 수집", response = Object.class)
    @GetMapping(value = "/cast")
    public ResponseEntity<Object> cast() throws IOException {
        log.info("출연진 데이터 수집 Scheduler 실행");
        List<Program> programList = programService.findAll();
        for (int i = 0; i < programList.size(); i++) {
            List<Cast> castList = castService.findByProgram_Id(programList.get(i).getId());
            if (castList.size() == 0) {
                Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + programList.get(i).getTitle() + "출연진");
                Document document = connection.get();

                Elements castInfos = document.select(".list_image_info .item");
                for (Element castInfo : castInfos) {
                    String imgUrl = castInfo.select("img").attr("abs:src");
                    String playName = castInfo.select(".title_box .name a").text();
                    String actorName = castInfo.select(".title_box .sub_text").text();
                    if (actorName.equals("진행") || actorName.equals("출연") || actorName.equals("")) {
                        actorName = playName;
                    }
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

    @ApiOperation(value = "프로그램 방영정보 데이터 수집", response = Object.class)
    @GetMapping(value = "/programdetail")
    public ResponseEntity<Object> programDetail() throws IOException {
        log.info("프로그램 방영정보 데이터 수집 Scheduler 호출");
        List<Program> programList = programService.findAll();
        for (int i = 0; i < programList.size(); i++) {
            if (programList.get(i).getBookmarkCnt() == null) {
                programList.get(i).setBookmarkCnt(0);
            }
            if (programList.get(i).getRatingCnt() == null) {
                programList.get(i).setRatingCnt(0L);
            }
//            if (programList.get(i).getAge() != null) continue;
            Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + programList.get(i).getTitle());
            Document document = connection.get();

            if (programList.get(i).getBookmarkCnt() == null) {
                programList.get(i).setBookmarkCnt(0);
                programList.get(i).setRatingCnt(0L);
            }
            Elements subTitles = document.select(".title_area");
            if (subTitles.size() == 0) continue;
            Elements subTitle = subTitles.get(0).select(".sub_title span");
            if (subTitle.size() > 1) {
                String age = subTitle.get(2).text();
                programList.get(i).setAge(age);
            }
            Elements info = document.select(".info_group");
            if (info.size() > 0) {
                Elements infoDetail = info.get(0).select("dd span");
                if (infoDetail.size() > 1) {
                    String day = infoDetail.get(1).text();
                    if (!day.equals("") && day != null) {
                        programList.get(i).setEndFlag(true);
                        programList.get(i).setBroadcastingDay(day);
                    } else {
                        programList.get(i).setEndFlag(false);
                    }
                }
                String episode = info.get(0).select("dd .state").text();
                programList.get(i).setBroadcastingEpisode(episode);
            }
            programService.save(programList.get(i));
        }
        log.info("프로그램 방영정보 데이터 수집 Scheduler 성공");
        return ResponseEntity.ok()
                .body(new Message("succeeded"));
    }
}