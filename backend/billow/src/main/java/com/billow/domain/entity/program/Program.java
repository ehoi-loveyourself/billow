package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String genre;

    @NotNull
    private Integer age;

    @NotNull
    private String summary;

    @NotNull
    private String broadcastingDay;

    @NotNull
    private String broadcastingTime;

    @NotNull
    private String broadcastingStation;

    @NotNull
    private boolean endFlag;

    @NotNull
    private Float averageRating;

    @Builder
    public Program(Long id, String title, String genre, Integer age, String summary, String broadcastingDay, String broadcastingTime, String broadcastingStation,
    boolean endFlag, Float averageRating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.age = age;
        this.summary = summary;
        this.broadcastingDay = broadcastingDay;
        this.broadcastingTime = broadcastingTime;
        this.broadcastingStation = broadcastingStation;
        this.endFlag = endFlag;
        this.averageRating = averageRating;
    }

}
