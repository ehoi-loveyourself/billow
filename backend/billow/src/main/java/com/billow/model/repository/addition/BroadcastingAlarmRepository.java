package com.billow.model.repository.addition;

import com.billow.domain.entity.addition.BroadcastingAlarm;
import com.billow.domain.entity.addition.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BroadcastingAlarmRepository extends JpaRepository<BroadcastingAlarm, Long> {
    List<BroadcastingAlarm> findByUser_Id(Long userId);
}
