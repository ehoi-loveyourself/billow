package com.billow.controller.program;

import com.billow.domain.dto.program.CastResponse;
import com.billow.domain.dto.program.ProgramIWatchedRequest;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.program.Program;
import com.billow.model.service.program.RecommendService;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping("/users")
    public ResponseEntity<Object> recommendUsers() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/conditions")
    public ResponseEntity<Object> recommendConditions() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/conditions")
    public ResponseEntity<Object> addProgramIWatched(
//            @RequestHeader("Auth-access") String token,
            @RequestBody ProgramIWatchedRequest programIWatchedRequest) {
        log.info("특정 상황에 봤던 프로그램 추가 API 호출");
        Message response = recommendService.addProgramIWatched(1L, programIWatchedRequest);
        log.info("특정 상황에 봤던 프로그램 추가 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/new")
    public ResponseEntity<Object> recommendNew() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/popular")
    public ResponseEntity<Object> recommendPopular() {
        log.info("인기 프로그램 추천 API 호출");
        List<ProgramResponse> responses = recommendService.recommendPopular();
        log.info("인기 프로그램 추천 성공");
        return ResponseEntity.ok()
                .body(responses);
    }

    @GetMapping("/actor")
    public ResponseEntity<Object> recommendActor() {
        log.info("특정 배우 프로그램 추천 API 호출");
        List<CastResponse> response = recommendService.recommendActor(1L);
        log.info("특정 배우 프로그램 추천 API 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/gender-age")
    public ResponseEntity<Object> recommendGenderAge() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/onair")
    public ResponseEntity<Object> recommendOnair() {
        List<Program> response = recommendService.recommendOnair();
        return ResponseEntity.ok()
                .body(response);
    }
}
