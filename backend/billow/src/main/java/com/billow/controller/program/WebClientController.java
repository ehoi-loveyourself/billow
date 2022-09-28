package com.billow.controller.program;

import com.billow.domain.dto.program.ProgramResponse;
import com.billow.model.service.webClient.webClientService;
import com.billow.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/mf")
public class WebClientController {

    public final WebClient webClient;

    public final webClientService webClientService;

    @GetMapping("/user-recommend")
    public ResponseEntity<Object> userRecommend(@RequestHeader("Auth-access") String token) {
        log.info("사용자 평점 기반 프로그램 추천 API 호출");
        List<ProgramResponse> responses = webClientService.userProgramRecommend(JwtUtil.getUserId(token));
        log.info("추천리스트 호출 성공");
        return (ResponseEntity.ok()
                .body(responses));
    }

    @GetMapping("/condition-recommend/{programId}")
    public ResponseEntity<Object> conditionRecommend(@RequestHeader("Auth-access") String token, @PathVariable("programId") Long programId) {
        log.info("상황별 프로그램 추천 API 호출");
        List<ProgramResponse> responses = webClientService.conditionRecommend(JwtUtil.getUserId(token), programId);
        log.info("상황별 프로그램 추천 성공");
        return ResponseEntity.ok()
                .body(responses);
    }
}
