package com.billow.controller.addition;

import com.billow.domain.dto.addtion.ChatRequest;
import com.billow.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Api(tags = {"Chat API"})
@RestController
@RequestMapping("/chat")
public class ChatController {

    @ApiOperation(value = "온에어 톡 조회", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "온에어 톡 조회 성공")})
    @GetMapping("/{programId}")
    public ResponseEntity<Object> selectChat(@PathVariable("programId") Long programId) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @ApiOperation(value = "메시지 전송", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메시지 전송 성공")})
    @PostMapping("/{programId}")
    public ResponseEntity<Object> sendMessage(@PathVariable("programId") Long programId, @RequestBody ChatRequest chatRequest) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }
}
