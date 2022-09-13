package com.billow;

import com.billow.domain.entity.organization.ProgramOrganization;
import com.billow.model.service.organization.ProgramOrganozationService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@EnableScheduling
@SpringBootApplication
public class BillowApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BillowApplication.class, args);
    }
}
