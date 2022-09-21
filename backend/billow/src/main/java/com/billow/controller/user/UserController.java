package com.billow.controller.user;

import com.billow.domain.dto.user.AuthTokenResponse;
import com.billow.domain.dto.addtion.RatingRequest;
import com.billow.domain.dto.addtion.RatingResponse;
import com.billow.domain.dto.user.LoginResponse;
import com.billow.domain.dto.user.RefreshRequest;
import com.billow.model.service.user.UserService;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> selectUser() {
        Message response = new Message("sdsd");
        Message response1 = new Message("sdsd");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/oauth")
    public ResponseEntity<Object> kakaoLogin(String code) {
        try {
            log.info("카카오 로그인 API 호출");
            LoginResponse response = userService.kakaoLogin(code);
            log.info("카카오 로그인 API 성공");
            return ResponseEntity.ok()
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Message("로그인에 실패하였습니다."));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(@RequestBody RefreshRequest refreshRequest) {
        try {
            log.info("액세스 토큰 재발급 요청 API 호출");
            if (refreshRequest.getRefreshToken() == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new Message("재로그인 하시기 바랍니다."));
            }
            AuthTokenResponse response = userService.refresh(refreshRequest.getEmail(), refreshRequest.getRefreshToken());
            log.info("액세스 토큰 재발급 성공");
            return ResponseEntity.ok()
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Message("액세스 토큰 발급에 실패했습니다."));
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateUser() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @PutMapping("/profile")
    public ResponseEntity<Object> updateUserProfile() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/rating")
    public ResponseEntity<Object> selectRating(@RequestHeader("Auth-access") String token) {
        log.info("평점 조회 API 호출");
        List<RatingResponse> response = userService.selectRating(0L);
        log.info("평점 조회 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @PutMapping("/rating/{ratingId}")
    public ResponseEntity<Object> updateRating(@RequestHeader("Auth-access") String token,
                                               @PathVariable("ratingId") Long ratingId,
                                               @RequestBody RatingRequest ratingRequest) {
        log.info("평점 수정 API 호출");
        Message response = userService.updateRating(0L, ratingId, ratingRequest);
        log.info("평점 수정 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping("/rating/{ratingId}")
    public ResponseEntity<Object> deleteRating(@RequestHeader("Auth-access") String token, @PathVariable("ratingId") Long ratingId) {
        log.info("평점 삭제 API 호출");
        Message response = userService.deleteRating(0L, ratingId);
        return ResponseEntity.ok()
                .body(response);
    }
}
