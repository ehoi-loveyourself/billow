package com.billow.jwt;

import com.billow.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class JwtUtil implements InitializingBean {

    private static final String SECRET = "ssafy second project B309 Billow";
    private static final long EXPIRATION = 1000 * 60 * 60 * 2;
    private static final String EXPIRED_TOKEN = "토큰이 만료되었습니다.";

    private static Key key;

    @Override
    public void afterPropertiesSet() throws Exception {  // init()
        String encodedKey = Base64.getEncoder().encodeToString(SECRET.getBytes());
        key = Keys.hmacShaKeyFor(encodedKey.getBytes());
        // https://budnamu.tistory.com/entry/JWT 참고
    }

    public static String createAuthToken(Long id, String email, String name) {
        return create(id, email, name, "Auth-access", EXPIRATION);
    }

    public static String createRefreshToken() {
        return create(null, null, null, "refreshToken", EXPIRATION * 5);
    }

    public static Long getUserId(String token) {
        return ((Number) getAllClaims(token).get("id")).longValue();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e){
            // 만료된 경우에는 refresh token을 확인하기 위해
            throw new UnauthorizedException(EXPIRED_TOKEN);
        } catch (JwtException | IllegalArgumentException e) {
            throw e;
        }
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
