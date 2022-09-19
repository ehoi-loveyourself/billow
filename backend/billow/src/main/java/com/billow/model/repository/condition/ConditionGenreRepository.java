package com.billow.model.repository.condition;

import com.billow.domain.entity.condition.ConditionGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionGenreRepository extends JpaRepository<ConditionGenre, Long> {

    ConditionGenre findByGenre(String genre);
}
