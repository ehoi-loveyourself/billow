package com.billow.scheduler;

import com.billow.domain.entity.program.Cast;
import com.billow.model.service.program.CastService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CastScheduler {

    @Autowired
    private CastService castService;

    //    @Scheduled(cron = "0 0 0 * * *")
    public void Cast() throws IOException {
        String programTitle = "빅마우스";
        Connection connection = Jsoup.connect("https://search.naver.com/search.naver?query=" + programTitle + "출연진");
        Document document = connection.get();

        Elements castInfos = document.select(".list_image_info .item");
        for (Element castInfo : castInfos) {
            String imgUrl = castInfo.select("img").attr("abs:src");
            String playName = castInfo.select(".title_box a").text();
            String actorName = castInfo.select(".title_box span").text();
            System.out.println(imgUrl + " " + playName + " " + actorName);
            Cast cast = Cast.builder()
                    .actorName(actorName)
                    .playName(playName)
                    .imgUrl(imgUrl)
                    .build();
            castService.save(cast);
        }
    }
}