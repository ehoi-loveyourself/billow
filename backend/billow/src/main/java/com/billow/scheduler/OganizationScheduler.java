package com.billow.scheduler;

import com.billow.domain.entity.organization.ProgramOrganization;
import com.billow.model.service.organization.ProgramOrganozationService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class OganizationScheduler {

    @Autowired
    private ProgramOrganozationService programOrganozationService;

//    @Scheduled(fixedDelay = 1000)
//    public void runEvery10Sec(){
//        System.out.println("OganizationScheduer");
//    }

    //    @Scheduled(cron = "0 0 0 * * *")
    public void programOrganozationData() throws IOException {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd.");

        String yesterDay = today.minusDays(1).format(dateTimeFormatter);
        programOrganozationService.deleteByBroadcastingDayStartingWith(yesterDay);
        //TODO : 신규 프로그램이면 전체 편성표 넣어줘야 함.
        //TODO : 프로그램 조회 코드 추가
        String programTitle = "런닝맨";
        Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + programTitle + "방송시간");
        Document document = connection.get();

        Elements channel = document.select(".table_scroll_wrap>.table_top_area>.cm_table tr a");
        if (!channel.isEmpty()) {
            Elements broadcastingDay = document.select(".table_fixed_wrap span");
            Elements broadcastingDayRow = document.select(".table_scroll_wrap>.table_body_area>.cm_table tr"); //이 사이즈는 날짜 index와 매칭

            for (int d = 0; d < broadcastingDayRow.size(); d++) {//d는 날짜 인덱스와 같음
                if (!broadcastingDay.get(d).text().substring(0, 6).equals(today.plusDays(6).format(dateTimeFormatter)))
                    continue;
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
//                            programOrganozationService.save(programOrganization);
//                            System.out.println(programOrganization);
                        }
                    }
                }
            }
        } else {
            Elements broadcastingDayRow = document.select(".tvtime_list .info_list");
            for (int d = 0; d < broadcastingDayRow.size(); d++) {
                Elements broadcastingInfos = broadcastingDayRow.get(d).select(".info");
                String broadcastingDay = broadcastingDayRow.get(d).select(".cm_date").text();
                if (!broadcastingDay.substring(0, 6).equals(today.plusDays(6).format(dateTimeFormatter))) continue;
                for (Element broadcastingInfo : broadcastingInfos) {
                    ProgramOrganization programOrganization = ProgramOrganization.builder()
                            .broadcastingDay(broadcastingDayRow.get(d).select(".cm_date").text())
                            .broadcastingTime(broadcastingInfo.select(".time").text())
                            .broadcastingEpisode(broadcastingInfo.select(".number_text").text())
                            .broadcastingAge(broadcastingInfo.select(".age_limit").text())
                            .broadcastingRerun(broadcastingInfo.select(".blind").text())
                            .broadcastingStation(broadcastingInfo.select("a").text())
                            .build();
//                          programOrganozationService.save(programOrganization);
//                    System.out.println(programOrganization);
                }
            }
        }
    }
}