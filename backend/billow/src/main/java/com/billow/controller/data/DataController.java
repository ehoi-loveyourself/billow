package com.billow.controller.data;

import com.billow.domain.dto.program.GenderAgeViewerInformation;
import com.billow.domain.entity.organization.ProgramOrganization;
import com.billow.domain.entity.program.Cast;
import com.billow.domain.entity.program.GenderAgeViewer;
import com.billow.model.service.organization.ProgramOrganozationService;
import com.billow.model.service.program.CastService;
import com.billow.model.service.program.GenderAgeViewerService;
import com.billow.model.service.data.kDramaService;
import com.billow.model.service.data.kPopService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Transactional
@RestController
@RequestMapping("/data")
public class DataController {

    //TODO : Exception추가하기
    @Autowired
    private kDramaService kDramaService;

    @Autowired
    private kPopService kPopService;

    @Autowired
    private GenderAgeViewerService genderAgeViewerService;

    @Autowired
    private ProgramOrganozationService programOrganozationService;

    @Autowired
    private CastService castService;

    @GetMapping(value = "/kdrama")
    public ResponseEntity<Object> kdramaData() {
        try {
            List<GenderAgeViewerInformation> kDramaList = kDramaService.getData();
            for (GenderAgeViewerInformation drama : kDramaList) {
                GenderAgeViewer program = GenderAgeViewer.builder()
                        .programTitle(drama.getProgram_Title())
                        .area(drama.getArea())
                        .genreLclas(drama.getGenre_Lclas()).genreMlsfc(drama.getGenre_Mlsfc()).genreSclas(drama.getGenre_Sclas())
                        .male0(drama.getMale0()).male10(drama.getMale10()).male20(drama.getMale20()).male30(drama.getMale30()).male40(drama.getMale40()).male50(drama.getMale50()).male60(drama.getMale60())
                        .female0(drama.getFemale0()).female10(drama.getFemale10()).female20(drama.getFemale20()).female30(drama.getFemale30())
                        .female40(drama.getFemale40()).female50(drama.getFemale50()).female60(drama.getFemale60())
                        .build();
                genderAgeViewerService.save(program);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/kpop")
    public ResponseEntity<Object> kpopData() {
        try {
            List<GenderAgeViewerInformation> kPopList = kPopService.getData();
            for (GenderAgeViewerInformation pop : kPopList) {
                GenderAgeViewer program = GenderAgeViewer.builder()
                        .programTitle(pop.getProgram_Title())
                        .area(pop.getArea())
                        .genreLclas(pop.getGenre_Lclas()).genreMlsfc(pop.getGenre_Mlsfc()).genreSclas(pop.getGenre_Sclas())
                        .male0(pop.getMale0()).male10(pop.getMale10()).male20(pop.getMale20()).male30(pop.getMale30()).male40(pop.getMale40()).male50(pop.getMale50()).male60(pop.getMale60())
                        .female0(pop.getFemale0()).female10(pop.getFemale10()).female20(pop.getFemale20()).female30(pop.getFemale30())
                        .female40(pop.getFemale40()).female50(pop.getFemale50()).female60(pop.getFemale60())
                        .build();
                genderAgeViewerService.save(program);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/programorganization")
    public ResponseEntity<Object> programorganization() {
        try {
            //하루 전 데이터 삭제
            LocalDate today = LocalDate.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd.");

            //TODO : 프로그램 조회 코드 추가
            //TODO : 컨티뉴 하지 말고 이미 있는건 하루 꺼만, 신규 프로그램은 전체 편성표 수집
            //TODO : 테스트용 코드, 스케줄러로 이동 예정
            String programTitle = "런닝맨";
            Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + programTitle + "방송시간");
            Document document = connection.get();

            Elements channel = document.select(".table_scroll_wrap>.table_top_area>.cm_table tr a");
            if (!channel.isEmpty()) {
                Elements broadcastingDay = document.select(".table_fixed_wrap span");
                Elements broadcastingDayRow = document.select(".table_scroll_wrap>.table_body_area>.cm_table tr"); //이 사이즈는 날짜 index와 매칭

                for (int d = 0; d < broadcastingDayRow.size(); d++) {//d는 날짜 인덱스와 같음
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
                    for (Element broadcastingInfo : broadcastingInfos) {
                        ProgramOrganization programOrganization = ProgramOrganization.builder()
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
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/cast")
    public ResponseEntity<Object> cast() {
        try {
            //TODO : 프로그램 조회 후 크롤링
            //TODO : 신규 프로그램 필터링 해서 업데이트
            //TODO : 테스트용 코드, 스케줄러로 이동 예정

            String programTitle = "런닝맨";
            Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + programTitle + "출연진");
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
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}