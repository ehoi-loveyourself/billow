package com.billow.model.service.user;

import com.billow.domain.entity.user.User;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.user.UserRepository;
import com.billow.util.KakaoOAuth2;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private static final String EMAIL_NOT_FOUND = "이메일 수집에 동의해주세요.";

    private final KakaoOAuth2 kakaoOAuth2;
    private final UserRepository userRepository;

    public Message kakaoLogin(String code) throws ParseException {
        User kakaoUser = kakaoOAuth2.getUserInfo(code);
        log.info(kakaoUser.toString());

        if (kakaoUser.getEmail() == null) {
            throw new NotFoundException(EMAIL_NOT_FOUND);
        } else {
            Optional<User> user = userRepository.findByEmail(kakaoUser.getEmail());
            if (user.isPresent()) {
                //로그인으로 진행
            } else {
                User singUpUser = User.builder()
                        .name(kakaoUser.getNickName())
                        .email(kakaoUser.getEmail())
                        .build();
                userRepository.save(singUpUser);
            }
            //TODO: 토큰 발급 과정 추가
            return new Message("카카오 로그인 성공하였습니다.");
        }
    }
}
