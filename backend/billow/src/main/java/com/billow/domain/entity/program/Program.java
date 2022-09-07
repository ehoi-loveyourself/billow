package com.billow.domain.entity.program;

import com.billow.domain.entity.user.ProfileImg;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_program")
@Entity
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

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poster_img_id")
    private PosterImg posterImg;

    @Builder
    public Program(Long id, String title, String genre, Integer age, String summary, String broadcastingDay, String broadcastingTime, String broadcastingStation, boolean endFlag, Float averageRating, PosterImg posterImg) {
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
        this.posterImg = posterImg;
    }
}
