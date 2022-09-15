package com.billow.util;

import com.billow.domain.entity.user.User;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONObject;

@Component
public class KakaoOAuth2 {

    public User getUserInfo(String code) throws ParseException {
        String accessToken = getAccessToken(code);
        System.out.println(accessToken);
        // 2. 액세스 토큰 -> 카카오 사용자 정보
        User userInfo = getUserInfoByToken(accessToken);

        return userInfo;
    }
    private User getUserInfoByToken(String accessToken) throws ParseException {
        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.getBody());
//        JSONObject body = new JSONObject(response.getBody());
//        Long id = body.getLong("id");
        String email = (String) ((JSONObject) jsonObject.get("kakao_account")).get("email");
        String nickname = (String) ((JSONObject) jsonObject.get("properties")).get("nickname");
        String gender = (String) ((JSONObject) jsonObject.get("properties")).get("gender");
        String age = (String) ((JSONObject) jsonObject.get("properties")).get("age_range");
        System.out.println(age);
        User user = User.builder()
                .email(email)
                .nickName(nickname)
                .gender(gender)
                .build();
        return user;
    }
    private String getAccessToken(String authorizedCode) throws ParseException {
        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "87e9dca8354b754ff65f4e45e12eb8a5");
        params.add("redirect_uri", "http://localhost:8009/users/oauth");
        params.add("code", authorizedCode);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // JSON -> 액세스 토큰 파싱
        String tokenJson = response.getBody();
//        JSONObject rjson = new JSONObject(tokenJson);

        JSONParser jsonParser = new JSONParser();
        JSONObject rjson = (JSONObject) jsonParser.parse(response.getBody());
        String accessToken = (String) rjson.get("access_token");

        return accessToken;
    }
}
