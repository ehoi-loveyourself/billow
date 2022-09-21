package com.billow.util;

import com.billow.domain.entity.user.User;
import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class JwtUtil {

    private static final String SECRET = "ssafy second project B309 Billow";
    private static final long EXPIRATION = 1000 * 60 * 60 * 2;

    public static String createAuthToken(String email, String name) {
        return create(email, name, "Auth-access", EXPIRATION);
    }

    public static String createRefreshToken() {
        return create(null, null, "refreshToken", EXPIRATION * 5);
    }

    public static Map<String, Object> checkAndGetClaims(String jwt) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(jwt);
        log.info("claims: {}", claims);
        return claims.getBody();
    }

    private static String create(String email, String name, String subject, long expirarion) {
        final JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
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

}
