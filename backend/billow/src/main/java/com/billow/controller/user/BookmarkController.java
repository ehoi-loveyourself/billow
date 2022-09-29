package com.billow.controller.user;

import com.billow.domain.dto.user.BookmarkResponse;
import com.billow.jwt.JwtTokenProvider;
import com.billow.model.service.user.BookmarkService;
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
@Api(tags = {"Bookmark API"})
@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @ApiOperation(value = "즐겨찾기 조회", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "즐겨찾기 조회 성공")})
    @GetMapping
    public ResponseEntity<Object> selectBookmark(@RequestHeader("Auth-access") String token) {
        log.info("즐겨찾기 조회 API 호출");
        List<BookmarkResponse> responses = bookmarkService.selectBookmark(JwtTokenProvider.getUserId(token));
        log.info("즐겨찾기 조회 성공");
        return ResponseEntity.ok()
                .body(responses);
    }

    @ApiOperation(value = "즐겨찾기 등록", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "즐겨찾기 등록 성공")})
    @PostMapping("/{programId}")
    public ResponseEntity<Object> postBookmark(@RequestHeader("Auth-access") String token, @PathVariable("programId") Long programId) {
        log.info("즐겨찾기 등록 API 호출");
        Message response = bookmarkService.postBookmark(JwtTokenProvider.getUserId(token), programId);
        log.info("즐겨찾기 등록 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @ApiOperation(value = "즐겨찾기 삭제", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "즐겨찾기 삭제 성공")})
    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<Object> deleteBookmark(@RequestHeader("Auth-access") String token, @PathVariable("bookmarkId") Long bookmarkId) {
        log.info("즐겨찾기 삭제 API 호출");
        Message response = bookmarkService.deleteBookmark(JwtTokenProvider.getUserId(token), bookmarkId);
        log.info("즐겨찾기 삭제 성공");
        return ResponseEntity.ok()
                .body(response);
    }
}