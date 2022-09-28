package com.billow.controller;

import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/sql")
public class SQLController {
    // 삭제 예정
    @GetMapping("/user")
    public ResponseEntity<Object> insertUser() {

        for (int i = 1; i < 501; i++) {
            String email = "";
            for (int e = 1; e <= 6; e++) {
                email += (char) ((Math.random() * 26) + 65);
            }
            int num = 0;
            for (int e = 1; e <= 6; e++) {
                num = (char) (Math.random() * 9999);
            }
            int region = (int) ((Math.random() * 10) + 1);
            int tv = (int) ((Math.random() * 15) + 1);
            String[] gender = {"여", "남"};
            int[] age = {0, 10, 20, 30, 40, 50, 60};
            int age1 = (int) ((Math.random() * 7));
            int mobile = (char) (Math.random() * 99999999)+10000001;

            int gender1 = (int) ((Math.random() * 2));
            int profile = i % 20 + 1;

            String d = "INSERT INTO tb_user VALUES (" + i + ", " + age[age1] + ", '" + email + num + "@naver.com', '" + gender[gender1] + "', '" + "000" + mobile + "', 'User_" + i + "', 'User_" + i + "', null, " + profile + ", " + region + ", " + tv + ");";
            System.out.println(d);
        }

        Message response = new Message("succeeded");
        return ResponseEntity.ok()
                .body(response);
    }
}
