package com.billow.controller.addition;

import com.billow.domain.dto.addtion.AlarmRequest;
import com.billow.domain.dto.addtion.BroadcastingAlarmResponse;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.model.service.addtion.BroadcastingAlarmService;
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
@RequestMapping("/alarm")
public class BroadcastingAlarmController {

    private final BroadcastingAlarmService broadcastingAlarmService;

    @GetMapping
    public ResponseEntity<Object> selectAlarm(
//            @RequestHeader("Auth-access") String token
            ) {
        log.info("방영 알림 조회 API 호출");
        List<BroadcastingAlarmResponse> responses = broadcastingAlarmService.selectAlarm(1L);
        log.info("방영 알림 조회 성공");
        return ResponseEntity.ok()
                .body(responses);
    }

    @PostMapping
    public ResponseEntity<Object> postAlarm(@RequestBody AlarmRequest alarmRequest) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping("/{alarmId}")
    public ResponseEntity<Object> deleteAlarm(@PathVariable("alarmId") Long alarmId) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }
}
