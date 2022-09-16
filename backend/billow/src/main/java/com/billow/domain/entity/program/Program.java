package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private Long ratingCnt;

    private String posterImg;

    private String backdropPath;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Genre> genreList = new ArrayList<>();

    @Builder
    public Program(Long id, String title, Integer age, String summary, String broadcastingDay, String broadcastingTime, String broadcastingStation,
                   boolean endFlag, Float averageRating, Long ratingCnt, String posterImg, String backdropPath, List<Genre> genreList) {
        this.id = id;
        this.title = title;
        this.age = age;
        this.summary = summary;
        this.broadcastingDay = broadcastingDay;
        this.broadcastingTime = broadcastingTime;
        this.broadcastingStation = broadcastingStation;
        this.endFlag = endFlag;
        this.averageRating = averageRating;
        this.ratingCnt = ratingCnt;
        this.posterImg = posterImg;
        this.backdropPath = backdropPath;
        this.genreList = genreList;
    }

    public void updateAverageRatingByPost(Float score) {
        averageRating = (getWholeRatings() + score) / ++ratingCnt;
    }

    public void updateAverageRatingByDelete(Float score) {
        if (ratingCnt < 2) {
            averageRating = 0f;
        } else {
            averageRating = (getWholeRatings() - score) / --ratingCnt;
        }
    }

    private float getWholeRatings() {
        return averageRating * ratingCnt;
    }
}
