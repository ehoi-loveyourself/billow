package com.billow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

//        @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/user/**")
//        ;
//    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/api/recommend/popular");
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/users/validation/**").permitAll()
//                .antMatchers("/api/users/oauth/**").permitAll()
//                .antMatchers("/api/users/signup").permitAll()
//                .antMatchers("/api/users/refresh").permitAll()
//                .antMatchers("/api/recommend/new").permitAll()
//                .antMatchers("/api/recommend/popular").permitAll()
//                .antMatchers("/api/recommend/onair").permitAll()
//                .antMatchers("/api/organization/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/chat/**").permitAll()
//                .antMatchers("/api/data/**").permitAll()
//                .anyRequest().authenticated()
        ;
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                .antMatchers(UrlMapping.AUTH + UrlMapping.SIGN_UP).permitAll()
//                .antMatchers(UrlMapping.AUTH + UrlMapping.LOGIN).permitAll()
//                .antMatchers(UrlMapping.VALIDATE_JWT).permitAll()
//                .antMatchers("/api/test/**").permitAll()
//                .anyRequest().authenticated();
//
//        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .httpBasic().disable()
//                .csrf().disable()
//        ;
//    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
