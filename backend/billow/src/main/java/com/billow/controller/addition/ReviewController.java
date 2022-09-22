package com.billow.controller.addition;

import com.billow.domain.dto.addtion.ReviewRequest;
import com.billow.domain.dto.addtion.ReviewResponse;
import com.billow.model.service.addtion.ReviewService;
import com.billow.util.JwtUtil;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{programId}")
    public ResponseEntity<Object> selectReview(@PathVariable("programId") Long programId) {
        log.info("리뷰 조회 API 호출");
        List<ReviewResponse> responses = reviewService.selectReview(programId);
        log.info("리뷰 조회 성공");
        return ResponseEntity.ok()
                .body(responses);
    }

    @PostMapping("/{programId}")
    public ResponseEntity<Object> postReview(@RequestHeader("Auth-access") String token, @PathVariable("programId") Long programId, @RequestBody ReviewRequest reviewRequest) {
        log.info("리뷰 등록 API 호출");
        Message response = reviewService.postReview(JwtUtil.getUserId(token), programId, reviewRequest);
        log.info("리뷰 등록 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Object> updateReview(@RequestHeader("Auth-access") String token, @PathVariable("reviewId") Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        log.info("리뷰 수정 API 호출");
        Message response = reviewService.updateReview(JwtUtil.getUserId(token), reviewId, reviewRequest);
        log.info("리뷰 수정 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Object> deleteReview(@RequestHeader("Auth-access") String token, @PathVariable("reviewId") Long reviewId) {
        log.info("리뷰 삭제 API 호출");
        Message response = reviewService.deleteReview(JwtUtil.getUserId(token), reviewId);
        log.info("리뷰 삭제 성공");
        return ResponseEntity.ok()
                .body(response);
    }
}
