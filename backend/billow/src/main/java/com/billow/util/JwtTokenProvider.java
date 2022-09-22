package com.billow.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * 토큰을 생성하고 검증하는 클래스
 * JwtAuthenticationFilter 에서 사전 검증을 거칩니다.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static String secretKey = "ssafy second project B309 Billow";
    private static byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    private static Key key = Keys.hmacShaKeyFor(keyBytes);
    private static long EXPIRATION = 1000 * 60 * 60 * 2L;

    private final UserDetailsService userDetailsService;

    // 객체를 초기화할 때, secretKey를 Base64로 인코딩한다.
//    @PostConstruct
//    protected void init() {
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//    }

    // JWT 액세스 토큰 생성
    public static String createAuthToken(Long id, String email, String name, List<String> roles) {
        return create(id, email, name, roles, "Auth-access", EXPIRATION);
    }

    // 리프레시 토큰 생성
    public static String createRefreshToken() {
        return create(null, null, null, null, "refreshToken", EXPIRATION * 5);
    }

    // JWT 토큰에서 인증정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public static Long getUserId(String token) {
        return ((Number) getAllClaims(token).get("id")).longValue();
    }

    public static String getUserName(String token) {
        return getAllClaims(token).get("name").toString();
    }

    // Request의 헤더에서 토큰값을 가져옵니다.
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Auth-access");
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private static Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static String create(Long id, String email, String name, List<String> roles, String subject, long expiration) {
        final JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .claim("id", id)
                .claim("email", email)
                .claim("name", name)
                .claim("roles", roles)
                .signWith(key, SignatureAlgorithm.HS256);
        final String jwt = builder.compact();
        if (email == null) {
            log.info("리프레시 토큰 발행: {}", jwt);
        } else {
            log.info("액세스 토큰 발행: {}", jwt);
        }
        return jwt;
    }
}
