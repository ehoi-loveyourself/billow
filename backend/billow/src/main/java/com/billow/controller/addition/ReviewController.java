package com.billow.controller.addition;

import com.billow.domain.dto.addtion.ReviewRequest;
import com.billow.domain.dto.addtion.ReviewResponse;
import com.billow.model.service.addtion.ReviewService;
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
@Api(tags = {"Review API"})
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation(value = "리뷰 조회", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 조회 성공")})
    @GetMapping("/{programId}")
    public ResponseEntity<Object> selectReview(@PathVariable("programId") Long programId) {
        log.info("리뷰 조회 API 호출");
        List<ReviewResponse> responses = reviewService.selectReview(programId);
        log.info("리뷰 조회 성공");
        return ResponseEntity.ok()
                .body(responses);
    }

    @ApiOperation(value = "리뷰 등록", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 등록 성공")})
    @PostMapping("/{programId}")
    public ResponseEntity<Object> postReview(@RequestHeader("Auth-access") String token, @PathVariable("programId") Long programId, @RequestBody ReviewRequest reviewRequest) {
        log.info("리뷰 등록 API 호출");
        Message response = reviewService.postReview(JwtUtil.getUserId(token), programId, reviewRequest);
        log.info("리뷰 등록 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @ApiOperation(value = "리뷰 수정", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 수정 성공")})
    @PutMapping("/{reviewId}")
    public ResponseEntity<Object> updateReview(@RequestHeader("Auth-access") String token, @PathVariable("reviewId") Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        log.info("리뷰 수정 API 호출");
        Message response = reviewService.updateReview(JwtUtil.getUserId(token), reviewId, reviewRequest);
        log.info("리뷰 수정 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @ApiOperation(value = "리뷰 삭제", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 삭제 성공")})
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Object> deleteReview(@RequestHeader("Auth-access") String token, @PathVariable("reviewId") Long reviewId) {
        log.info("리뷰 삭제 API 호출");
        Message response = reviewService.deleteReview(JwtUtil.getUserId(token), reviewId);
        log.info("리뷰 삭제 성공");
        return ResponseEntity.ok()
                .body(response);
    }
}
