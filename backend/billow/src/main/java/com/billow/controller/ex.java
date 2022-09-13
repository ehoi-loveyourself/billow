package com.billow.controller;

import com.billow.util.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ex {

    @PostMapping("/login")
    public ResponseEntity<Object> login() {
        Message response = null;
        return ResponseEntity.ok()
                .body(response);
    }
}
