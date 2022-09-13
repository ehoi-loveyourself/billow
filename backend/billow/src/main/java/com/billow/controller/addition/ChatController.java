package com.billow.controller.addition;

import com.billow.domain.dto.addtion.ChatRequest;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    @GetMapping("/{programId}")
    public ResponseEntity<Object> selectChat(@PathVariable("programId") Long programId) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/{programId}")
    public ResponseEntity<Object> postReview(@PathVariable("programId") Long programId, @RequestBody ChatRequest chatRequest) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }
}
