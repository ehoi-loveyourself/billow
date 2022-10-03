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
import org.springframework.web.bind.annotation.DeleteMapping;
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
}