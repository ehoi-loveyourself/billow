package com.billow.controller.user;

import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    @GetMapping
    public ResponseEntity<Object> selectBookmark() {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/{programId}")
    public ResponseEntity<Object> postBookmark(@PathVariable("programId") Long programId) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }

    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<Object> deleteBookmark(@PathVariable("bookmarkId") Long bookmarkId) {
        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }
}
