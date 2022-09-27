package com.billow.jwt;

import com.billow.exception.NotFoundException;
import com.billow.model.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private final UserRepository userRepository;

    /**
     * Spring-Security의 유저 인증 처리 과정중 유저객체를 만드는 과정
     * !! 보통 구글링시 UserDetails 클래스를 따로 만들어서 사용하지만 UserDetails 인터페이스를 구현한
     * User 라는 클래스를 시큐리티가 제공해줘서 굳이 만들어주지 않음
     * @param email userId
     * @return UserDetails (security에서 사용하는 유저 정보를 가진 객체)
     * @throws UsernameNotFoundException userId로 유저를 찾지 못했을 경우 발생하는 에러
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.billow.domain.entity.user.User billowUser = userRepository.findByEmail(email);

        if (billowUser == null) {
            throw new NotFoundException(USER_NOT_FOUND);
        }

        return createUserDetails(billowUser);
    }

    private UserDetails createUserDetails(com.billow.domain.entity.user.User user) {
        // 권한 관리 테이블로 만든 깃
        // -> https://github.com/szerhusenBC/jwt-spring-security-demo/blob/master/src/main/java/org/zerhusen/security/model/User.java
        List<SimpleGrantedAuthority> grantedAuthorities = user.getRoleList()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

        return new User(user.getEmail(),
                user.getName(),
                grantedAuthorities);
    }
}
