package com.billow.jwt;

import com.billow.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthInterceptor implements HandlerInterceptor {

    private static final String CHAT_REQUEST = "/api/chat/**";
    private static final String PROGRAM_REQUEST = "/api/program/**";
    private static final String REVIEW_REQUEST = "/api/review/**";
    private static final String HEADER = "Auth-access";
    private static final String NO_TOKEN = "재로그인이 필요합니다.";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        log.info("요청 URI: " + requestURI);
        log.info("요청 Method:" + requestMethod);
        if (CHAT_REQUEST.equals(requestURI) && HttpMethod.GET.matches(requestMethod)
                || PROGRAM_REQUEST.equals(requestURI) && HttpMethod.GET.matches(requestMethod)
                || REVIEW_REQUEST.equals(requestURI) && HttpMethod.GET.matches(requestMethod)
                || requestURI.equals("/api/users/validation/**")
                || requestURI.equals("/api/users/oauth")
                || requestURI.equals("/api/users/signup")
                || requestURI.equals("/api/users/refresh")
                || requestURI.equals("/api/profile/**")
                || requestURI.equals("/api/recommend/new")
                || requestURI.equals("/api/recommend/popular")
                || requestURI.equals("/api/recommend/onair")
                || requestURI.equals("/api/organization/**")) {
            // TODO 그냥 바로 true 줄 애들
            return true;
        }

        String token = request.getHeader(HEADER);
        if (token == null) {
            throw new BadRequestException(NO_TOKEN);
        }
        if (JwtUtil.validateToken(token)) {
            return true;
        }
        return true;
    }
}
