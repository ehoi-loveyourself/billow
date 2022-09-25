package com.billow.controller.program;

import com.billow.domain.dto.organization.OrganizationResponse;
import com.billow.domain.dto.program.CastResponse;
import com.billow.domain.dto.program.ProgramIWatchedRequest;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.program.Program;
import com.billow.domain.entity.user.User;
import com.billow.exception.NotFoundException;
import com.billow.model.service.program.RecommendService;
import com.billow.model.service.user.UserService;
import com.billow.util.JwtUtil;
import com.billow.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Api(tags = {"Recommend API"})
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @ApiOperation(value = "사용자 맞춤 추천", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 맞춤 추천 성공")})
    @GetMapping("/users")
    public ResponseEntity<Object> recommendUsers() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @ApiOperation(value = "상황별 추천", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상황별 프로그램 추천 성공")})
    @GetMapping("/conditions")
    public ResponseEntity<Object> recommendConditions() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @ApiOperation(value = "특정 상황에 봤던 프로그램 추가", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 상황에 봤던 프로그램 추가 성공")})
    @PostMapping("/conditions")
    public ResponseEntity<Object> addProgramIWatched(@RequestHeader("Auth-access") String token, @RequestBody ProgramIWatchedRequest programIWatchedRequest) {
        log.info("특정 상황에 봤던 프로그램 추가 API 호출");
        Message response = recommendService.addProgramIWatched(JwtUtil.getUserId(token), programIWatchedRequest);
        log.info("특정 상황에 봤던 프로그램 추가 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @ApiOperation(value = "신규 프로그램 추천", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "신규 프로그램 추천 성공")})
    @GetMapping("/new")
    public ResponseEntity<Object> recommendNew() {
        log.info("신규 프로그램 추천 API 호출");
        List<ProgramResponse> responses = recommendService.recommendNew();
        log.info("신규 프로그램 추천 성공");
        return ResponseEntity.ok()
                .body(responses);
    }

    @ApiOperation(value = "인기 프로그램 추천", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인기 프로그램 추천 성공")})
    @GetMapping("/popular")
    public ResponseEntity<Object> recommendPopular() {
        log.info("인기 프로그램 추천 API 호출");
        List<ProgramResponse> responses = recommendService.recommendPopular();
        log.info("인기 프로그램 추천 성공");
        return ResponseEntity.ok()
                .body(responses);
    }

    @ApiOperation(value = "특정 배우 프로그램 추천", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 배우 추천 성공")})
    @GetMapping("/actor")
    public ResponseEntity<Object> recommendActor(@RequestHeader("Auth-access") String token) {
        log.info("특정 배우 프로그램 추천 API 호출");
        List<CastResponse> response = recommendService.recommendActor(JwtUtil.getUserId(token));
        log.info("특정 배우 프로그램 추천 API 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @ApiOperation(value = "성연령별 프로그램 추천", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성연령별 프로그램 추천 성공")})
    @GetMapping("/gender-age")
    public ResponseEntity<Object> recommendGenderAge(@RequestHeader("Auth-access") String token) {
        log.info("성연령별 프로그램 추천 API 호출");
        List<ProgramResponse> response = recommendService.recommendGenderAge(JwtUtil.getUserId(token));
        log.info("성연령별 프로그램 추천 API 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @ApiOperation(value = "온에어 프로그램 추천", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "온에어 프로그램 추천 성공")})
    @GetMapping("/onair")
    public ResponseEntity<Object> recommendOnair() {
        log.info("온에어 프로그램 추천 API 호출");
        List<OrganizationResponse> response = recommendService.recommendOnair();
        log.info("온에어 프로그램 추천 API 성공");
        return ResponseEntity.ok()
                .body(response);
    }
}