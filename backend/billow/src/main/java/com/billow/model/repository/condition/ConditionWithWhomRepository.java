package com.billow.model.repository.condition;

import com.billow.domain.entity.condition.ConditionWithWhom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionWithWhomRepository extends JpaRepository<ConditionWithWhom, Long> {

    ConditionWithWhom findByWho(String who);
}
