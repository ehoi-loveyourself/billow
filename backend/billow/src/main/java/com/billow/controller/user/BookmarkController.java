package com.billow.controller.user;

import com.billow.domain.dto.program.ProgramResponse;
import com.billow.model.service.user.BookmarkService;
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
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public ResponseEntity<Object> selectBookmark(@RequestHeader("Auth-access") String token) {
        log.info("즐겨찾기 조회 API 호출");
        List<ProgramResponse> responses = bookmarkService.selectBookmark(JwtUtil.getUserId(token));
        log.info("즐겨찾기 조회 성공");
        return ResponseEntity.ok()
                .body(responses);
    }

    @PostMapping("/{programId}")
    public ResponseEntity<Object> postBookmark(@RequestHeader("Auth-access") String token, @PathVariable("programId") Long programId) {
        log.info("즐겨찾기 등록 API 호출");
        Message response = bookmarkService.postBookmark(JwtUtil.getUserId(token), programId);
        log.info("즐겨찾기 등록 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<Object> deleteBookmark(@RequestHeader("Auth-access") String token, @PathVariable("bookmarkId") Long bookmarkId) {
        log.info("즐겨찾기 삭제 API 호출");
        Message response = bookmarkService.deleteBookmark(JwtUtil.getUserId(token), bookmarkId);
        log.info("즐겨찾기 삭제 성공");
        return ResponseEntity.ok()
                .body(response);
    }
}
