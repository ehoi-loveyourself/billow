package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_program")
@Builder
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

    @Column(nullable = false, columnDefinition = "TINYINT", length=1)
    private boolean endFlag;

    @NotNull
    private  float average_rating;



}
