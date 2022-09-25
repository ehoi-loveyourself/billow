package com.billow.model.repository.user;

import com.billow.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);

    boolean existsByNickName(String nickname);
}