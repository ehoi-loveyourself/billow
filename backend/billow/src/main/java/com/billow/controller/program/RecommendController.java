package com.billow.controller.program;

import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/recommend")
public class RecommendController {

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
    public ResponseEntity<Object> recommendActor() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/gender-age")
    public ResponseEntity<Object> recommendGenderAge() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }
}
