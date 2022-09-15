package com.billow.model.repository.addition;

import com.billow.domain.entity.addition.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
