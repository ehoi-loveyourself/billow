package com.billow.controller.user;

import com.billow.domain.dto.addtion.RatingRequest;
import com.billow.model.service.user.UserService;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/oauth")
    public ResponseEntity<Object> kakaoLogin(String code) {
        try {
            log.info("카카오 로그인 API 호출");
            Message response = userService.kakaoLogin(code);
            log.info("카카오 로그인 API 성공");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Message("로그인에 실패하였습니다."));
        }
    }

    @GetMapping
    public ResponseEntity<Object> selectUser() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
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
    public ResponseEntity<Object> selectRating() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @PutMapping("/rating/{ratingId}")
    public ResponseEntity<Object> updateRating(@PathVariable("ratingId") Long ratingId, @RequestBody RatingRequest ratingRequest) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping("/rating/{ratingId}")
    public ResponseEntity<Object> deleteRating(@PathVariable("ratingId") Long ratingId) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }
}
