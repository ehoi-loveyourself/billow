package com.billow.domain.entity.program;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_program")
@Entity
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;

    private Integer programNum;

    private String title;

    private String age;

    @Column(length = 1000)
    private String summary;

    private String broadcastingDay;

    private String broadcastingEpisode;

    private String broadcastingStation;

    private boolean endFlag;

    private Float averageRating;

    private Long ratingCnt;

    private Integer bookmarkCnt;

    private String posterImg;

    private String backdropPath;

    private Date firstAirDate;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Genre> genreList = new ArrayList<>();

    @Builder
    public Program(Long id, Integer programNum, String title, String age, String summary, String broadcastingDay, String broadcastingEpisode, String broadcastingStation,
                   boolean endFlag, Float averageRating, Long ratingCnt, String posterImg, String backdropPath, List<Genre> genreList, Date firstAirDate) {
        this.id = id;
        this.programNum = programNum;
        this.title = title;
        this.age = age;
        this.summary = summary;
        this.broadcastingDay = broadcastingDay;
        this.broadcastingEpisode = broadcastingEpisode;
        this.broadcastingStation = broadcastingStation;
        this.endFlag = endFlag;
        this.averageRating = averageRating;
        this.ratingCnt = ratingCnt;
        this.bookmarkCnt = bookmarkCnt;
        this.posterImg = posterImg;
        this.backdropPath = backdropPath;
        this.genreList = genreList;
        this.firstAirDate = firstAirDate;
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

    public void addBookmark() {
        bookmarkCnt++;
    }

    public void deleteBookmark() {
        if (bookmarkCnt <= 0) {
            bookmarkCnt = 0;
        } else {
            bookmarkCnt--;
        }
    }
}
