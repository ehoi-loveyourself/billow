//package com.billow.util;
//
//import com.billow.domain.entity.user.User;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//@Component
//public class KakaoOAuth2 {
//
//    @Value("${kakao.client-id}")
//    private String CLIENT_ID;
//
//    @Value("${kakao.client-secret}")
//    private String CLIENT_SECRET;
//
//    @Value("${kakao.redirect-uri}")
//    private String CLIENT_REDIRECT_URL;
//
//    @Value("${kakao.token-uri}")
//    private String CLIENT_TOKEN_URL;
//
//    @Value("${kakao.user-info-uri}")
//    private String CLIENT_USERINFO_URL;
//
//    public User getUserInfo(String code) throws ParseException {
//        String accessToken = getAccessToken(code);
//        User userInfo = getUserInfoByToken(accessToken);
//        return userInfo;
//    }
//
//    private User getUserInfoByToken(String accessToken) throws ParseException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + accessToken);
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        RestTemplate rt = new RestTemplate();
//        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);
//
//        ResponseEntity<String> response = rt.exchange(CLIENT_USERINFO_URL, HttpMethod.POST, kakaoProfileRequest, String.class);
//
//        JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.getBody());
//        String email = (String) ((JSONObject) jsonObject.get("kakao_account")).get("email");
//        String nickname = (String) ((JSONObject) jsonObject.get("properties")).get("nickname");
//        String gender = (String) ((JSONObject) jsonObject.get("properties")).get("gender");
//        String age = (String) ((JSONObject) jsonObject.get("properties")).get("age_range");
//
//        User user = User.builder()
//                .email(email)
//                .nickName(nickname)
//                .gender(gender)
//                .build();
//        return user;
//    }
//
//    private String getAccessToken(String authorizedCode) throws ParseException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", CLIENT_ID);
//        params.add("redirect_uri", CLIENT_REDIRECT_URL);
//        params.add("client_secret", CLIENT_SECRET);
//        params.add("code", authorizedCode);
//
//        RestTemplate rt = new RestTemplate();
//        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
//
//        ResponseEntity<String> response = rt.exchange(CLIENT_TOKEN_URL, HttpMethod.POST, kakaoTokenRequest, String.class);
//
//        String tokenJson = response.getBody();
//
//        JSONParser jsonParser = new JSONParser();
//        JSONObject rjson = (JSONObject) jsonParser.parse(response.getBody());
//        String accessToken = (String) rjson.get("access_token");
//
//        return accessToken;
//    }
//}
