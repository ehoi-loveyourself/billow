package com.billow.domain.entity.addition;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_broadcasting_alarm")
@Entity
public class BroadcastingAlarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcasting_alarm_id")
    private Long id;

    @NotNull
    private String dateTime;

    @Builder
    public BroadcastingAlarm(Long id, String dateTime) {
        this.id = id;
        this.dateTime = dateTime;
    }
}
