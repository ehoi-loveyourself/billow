package com.billow.controller.program;

import com.billow.domain.dto.program.CastResponse;
import com.billow.domain.entity.program.Program;
import com.billow.model.service.program.RecommendService;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/new")
    public ResponseEntity<Object> recommendNew() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/popular")
    public ResponseEntity<Object> recommendPopular() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/actor")
    public ResponseEntity<Object> recommendActor(@RequestHeader("Auth-access") String token) {
        log.info("특정 배우 프로그램 추천 API 호출");
        List<CastResponse> response = recommendService.recommendActor(0L);
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
