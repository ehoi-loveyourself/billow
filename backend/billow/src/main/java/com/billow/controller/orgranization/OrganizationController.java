package com.billow.controller.orgranization;

import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @GetMapping
    public ResponseEntity<Object> selectOrganization() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/{programId}")
    public ResponseEntity<Object> selectProgramOrganization(@PathVariable("programId") Long programId) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/onair")
    public ResponseEntity<Object> selectOnair() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }
}
