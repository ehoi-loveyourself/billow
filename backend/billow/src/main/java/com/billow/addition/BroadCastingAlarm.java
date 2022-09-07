package com.billow.addition;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_broadcasting_alarm")
@Entity
public class BroadCastingAlarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcasting_alarm_id")
    private Long id;

    @NotNull
    private String dateTime;

    @Builder
    public BroadCastingAlarm(Long id, String dateTime) {
        this.id = id;
        this.dateTime = dateTime;
    }

}
