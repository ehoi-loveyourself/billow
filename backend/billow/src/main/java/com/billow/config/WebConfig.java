package com.billow.config;

import com.billow.jwt.JwtAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String[] EXCLUDED_PATHS = new String[]{
            "/api/users/validation/**",
            "/api/users/oauth",
            "/api/users/signup",
            "/api/users/refresh",
            "/api/profile/**",
            "/api/recommend/new",
            "/api/recommend/popular",
            "/api/recommend/onair",
            "/api/organization/**",
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://j7b309.p.ssafy.io", "https://j7b309.p.ssafy.io:80", "http://localhost:8080")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name()
                )
                .allowCredentials(true);
    }

    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(new JwtAuthInterceptor())
                .excludePathPatterns(EXCLUDED_PATHS);
    }
}
