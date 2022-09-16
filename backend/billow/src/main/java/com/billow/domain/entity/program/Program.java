package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_program")
@Entity
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;

    private String title;

    private Integer age;

    @Column(length = 1000)
    private String summary;

    private String broadcastingDay;

    private String broadcastingTime;

    private String broadcastingStation;

    private boolean endFlag;

    private Float averageRating;

    private String posterImg;

    @Builder
    public Program(Long id, String title, Integer age, String summary, String broadcastingDay, String broadcastingTime, String broadcastingStation, boolean endFlag, Float averageRating, String posterImg) {
        this.id = id;
        this.title = title;
        this.age = age;
        this.summary = summary;
        this.broadcastingDay = broadcastingDay;
        this.broadcastingTime = broadcastingTime;
        this.broadcastingStation = broadcastingStation;
        this.endFlag = endFlag;
        this.averageRating = averageRating;
        this.posterImg = posterImg;
    }
}
