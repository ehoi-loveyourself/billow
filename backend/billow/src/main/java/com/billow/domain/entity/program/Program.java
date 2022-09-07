package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_program")
@Builder
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private String broadcastingDay;

    @Column(nullable = false)
    private String broadcastingTime;

    @Column(nullable = false)
    private String broadcastingStation;

    @Column(nullable = false, columnDefinition = "TINYINT", length=1)
    private boolean endFlag;

    @Column(nullable = false)
    private  float average_rating;



}
