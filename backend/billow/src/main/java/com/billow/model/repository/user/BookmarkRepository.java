package com.billow.model.repository.user;

import com.billow.domain.entity.user.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUser_Id(Long userId);
}
