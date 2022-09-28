package com.billow.model.repository.addition;

import com.billow.domain.entity.addition.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByProgramId(Long programId);
}
