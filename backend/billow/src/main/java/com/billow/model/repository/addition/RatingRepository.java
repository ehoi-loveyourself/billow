package com.billow.model.repository.addition;

import com.billow.domain.entity.addition.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByUser_Id(Long userId);
}
