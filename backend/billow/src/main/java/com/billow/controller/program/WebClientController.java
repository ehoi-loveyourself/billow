package com.billow.controller.program;

import com.billow.domain.dto.program.ProgramResponse;
import com.billow.jwt.JwtTokenProvider;
import com.billow.model.service.webClient.webClientService;
import com.billow.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Api(tags = {"WebClient API"})
@RestController
@RequestMapping("/mf")
public class WebClientController {

    public final WebClient webClient;

    public final webClientService webClientService;

    @ApiOperation(value = "사용자 평점 기반 프로그램 추천", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "추천리스트 호출 성공"),
            @ApiResponse(responseCode = "400", description = "오류가 발생하였습니다.")
    })
    @GetMapping("/user-recommend/{userId}")
    public ResponseEntity<Object> userRecommend(@PathVariable("userId") Long userId) {
        log.info("사용자 평점 기반 프로그램 추천 API 호출");
        List<ProgramResponse> responses = webClientService.userProgramRecommend(userId);
        log.info("추천리스트 호출 성공");
        return (ResponseEntity.ok()
                .body(responses));
    }

    @ApiOperation(value = "상황별 프로그램 추천", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상황별 프로그램 추천 성공"),
            @ApiResponse(responseCode = "404", description = "해당 유저를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "404", description = "해당 프로그램을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "오류가 발생하였습니다.")
    })
    @GetMapping("/condition-recommend/{programId}")
    public ResponseEntity<Object> conditionRecommend(@RequestHeader("Auth-access") String token, @PathVariable("programId") Long programId) {
        log.info("상황별 프로그램 추천 API 호출");
        List<ProgramResponse> responses = webClientService.conditionRecommend(JwtTokenProvider.getUserId(token), programId);
        log.info("상황별 프로그램 추천 성공");
        return ResponseEntity.ok()
                .body(responses);
    }
}