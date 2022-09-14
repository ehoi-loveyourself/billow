package com.billow.controller.addition;

import com.billow.domain.dto.addtion.ReviewRequest;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {

    @GetMapping("/{programId}")
    public ResponseEntity<Object> selectReview(@PathVariable("programId") Long programId) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/{programId}")
    public ResponseEntity<Object> postReview(@PathVariable("programId") Long programId, @RequestBody ReviewRequest reviewRequest) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Object> updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Object> deleteReview(@PathVariable("reviewId") Long reviewId) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }
}
