package com.billow.model.service.user;

import com.billow.domain.entity.program.Cast;
import com.billow.domain.entity.user.User;
import com.billow.model.repository.program.CastRepository;
import com.billow.util.KakaoOAuth2;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final KakaoOAuth2 kakaoOAuth2;
    public void kakaoLogin(String code) throws ParseException {
        System.out.println(code);
        User kakaoUser = kakaoOAuth2.getUserInfo(code);
        System.out.println(kakaoUser);
    }
}
