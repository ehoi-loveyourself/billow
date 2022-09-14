package com.billow.controller.addition;

import com.billow.domain.dto.addtion.AlarmRequest;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/alarm")
public class BroadcastingAlarmController {

    @GetMapping
    public ResponseEntity<Object> selectAlarm() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
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
