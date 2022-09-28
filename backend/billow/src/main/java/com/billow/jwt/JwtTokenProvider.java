package com.billow.jwt;

import com.billow.domain.entity.user.User;
import com.billow.exception.NotFoundException;
import com.billow.exception.UnauthorizedException;
import com.billow.exception.WrongAccessException;
import com.billow.model.repository.user.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

// 토큰을 생성해주고 검증하는 등 토큰 관리 객체
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider implements InitializingBean {

    private static final String SECRET = "ssafy second project B309 Billow";
    private static final long EXPIRATION = 1000 * 60 * 60 * 2L;
//    private static final long EXPIRATION = 1000 * 5L; // 테스트 1초
    private static final String EXPIRED_TOKEN = "토큰이 만료되었습니다.";
    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private static final String RE_LOGIN = "다시 로그인 해주세요!";

    private static Key key;

    private final UserRepository userRepository;

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

//    public static boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (ExpiredJwtException e){
//            // 만료된 경우에는 refresh token을 확인하기 위해
//            throw new UnauthorizedException(EXPIRED_TOKEN);
//        } catch (JwtException | IllegalArgumentException e) {
//            throw e;
//        }
//    }

//    public static void validateToken(String token) {
//        try {
//            getAllClaims(token);
//        } catch (ExpiredJwtException e) {
//            throw new UnauthorizedException(EXPIRED_TOKEN);
//        }
//    }

    private static String create(Long id, String email, String name, String subject, long expiration) {
        final JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .claim("id", id)
                .claim("email", email)
                .claim("name", name)
                .signWith(SignatureAlgorithm.HS256, key);
//                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes(StandardCharsets.UTF_8));
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
//                .setSigningKey(SECRET.getBytes(StandardCharsets.UTF_8))
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    //////// 여기까지 JwtUtil


//    private final CustomUserDetailsService customUserDetailsService;
//
//    private final String secretKey;
//    private final long tokenValidityInMs;
//    private final long refreshTokenValidityInMs;
//
//    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey,
//                            @Value("${jwt.token-validity-in-sec}") long tokenValidity,
//                            @Value("${jwt.refresh-token-validity-in-sec}") long refreshTokenValidity,
//                            CustomUserDetailsService customUserDetailsService){
//        this.secretKey = secretKey;
//        this.tokenValidityInMs = tokenValidity * 1000;
//        this.refreshTokenValidityInMs = refreshTokenValidity * 1000;
//        this.customUserDetailsService = customUserDetailsService;
//    }


//    public String createAccessToken(Authentication authentication) {
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + tokenValidityInMs);
//
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .setIssuedAt(now) // 발행시간
//                .signWith(key, SignatureAlgorithm.HS512) // 암호화
//                .setExpiration(validity) // 만료
//                .compact();
//    }

    /**
     * 토큰으로 부터 Authentication 객체를 얻어온다.
     * Authentication 안에 user의 정보가 담겨있음.
     * UsernamePasswordAuthenticationToken 객체로 Authentication을 쉽게 만들수 있으며,
     * 매게변수로 UserDetails, pw, authorities 까지 넣어주면
     * setAuthenticated(true)로 인스턴스를 생성해주고
     * Spring-Security는 그것을 체크해서 로그인을 처리함
     */
    public Authentication getAuthentication(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();

        UserDetails userDetails = (UserDetails) userRepository.findById(getUserId(token))
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
//        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e){
            // 만료된 경우에는 refresh token을 확인하기 위해
            throw new UnauthorizedException(EXPIRED_TOKEN);
        } catch (JwtException | IllegalArgumentException e) {
            throw new WrongAccessException(RE_LOGIN);
        }
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Auth-access");
    }

//    public String createRefreshToken(Authentication authentication){
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + refreshTokenValidityInMs);
//
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .setIssuedAt(now)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .setExpiration(validity)
//                .compact();
//    }
}
