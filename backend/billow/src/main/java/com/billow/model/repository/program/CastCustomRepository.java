package com.billow.model.repository.program;

import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.program.Cast;

import java.util.List;

public interface CastCustomRepository {
    List<Cast> findByActorName(Long userId, String actorName);

    String findMaxCountByProgram_Id(List<Rating> ratingList);
}
