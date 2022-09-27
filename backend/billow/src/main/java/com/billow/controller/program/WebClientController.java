package com.billow.controller.program;

import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.program.Program;
import com.billow.model.service.webClient.webClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user-recommend")
public class WebClientController {

    public final WebClient webClient;

    public final webClientService webClientService;

    @GetMapping("/{userId}")
    public ResponseEntity<Object> userRecommend(@PathVariable("userId") Long userId) {
        log.info("사용자 평점 기반 프로그램 추천 API 호출");
        List<ProgramResponse> responses =  webClientService.userProgramRecommand(userId);
        log.info("추천리스트 호출 성공");
        return (ResponseEntity.ok()
                .body(responses));
        }
}
