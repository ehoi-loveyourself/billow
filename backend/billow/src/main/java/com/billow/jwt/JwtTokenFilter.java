package com.billow.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 토큰을 처리하는 필터
@Slf4j
@RequiredArgsConstructor
//public class JwtTokenFilter extends OncePerRequestFilter {
public class JwtTokenFilter extends GenericFilterBean {

//    public static final String AUTHORIZATION_HEADER = "Auth-access";

    private final JwtTokenProvider jwtTokenProvider;

//    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }

    /**
     * JWT를 검증하는 필터
     * HttpServletRequest 의 Authorization 헤더에서 JWT token을 찾고 그것이 맞는지 확인
     * UsernamePasswordAuthenticationFilter 앞에서 작동
     * (JwtTokenFilterConfigurer 참고)
     */
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String jwt = resolveToken(request, AUTHORIZATION_HEADER);
//
//        try {
//            if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
//                Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                log.info("set Authentication to security context for '{}', uri: {}", authentication.getName(), request.getRequestURI());
//            }
//        } catch (ExpiredJwtException e) {
//            request.setAttribute("exception", e);
//            log.info("ExpiredJwtException : {}", e.getMessage());
//        } catch (JwtException | IllegalArgumentException e) {
//            request.setAttribute("exception", e);
//            log.info("jwtException : {}", e.getMessage());
//        }
//
//        filterChain.doFilter(request, response);
//    }

//    private String resolveToken(HttpServletRequest request, String header) {
//        String bearerToken = request.getHeader(header);
//        if (bearerToken != null && bearerToken.startsWith("Bearer-")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
