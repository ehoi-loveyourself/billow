package com.billow.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class JwtUtil {

    private static final String SECRET = "ssafy second project B309 Billow";
    private static final long EXPIRATION = 1000 * 60 * 60 * 2;

    public static String createAuthToken(Long id, String email, String name) {
        return create(id, email, name, "Auth-access", EXPIRATION);
    }

    public static String createRefreshToken() {
        return create(null, null, null, "refreshToken", EXPIRATION * 5);
    }

    public static Long getUserId(String token) {
        return ((Number) getAllClaims(token).get("id")).longValue();
    }

    private static String create(Long id, String email, String name, String subject, long expiration) {
        final JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .claim("id", id)
                .claim("email", email)
                .claim("name", name)
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes(StandardCharsets.UTF_8));
        final String jwt = builder.compact();
        if (email == null) {
            log.info("리프레시 토큰 발행: {}", jwt);
        } else {
            log.info("auth 토큰 발행: {}", jwt);
        }
        return jwt;
    }

    private static Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
