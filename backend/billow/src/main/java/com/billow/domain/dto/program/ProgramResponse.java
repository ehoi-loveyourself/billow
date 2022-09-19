package com.billow.domain.dto.program;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgramResponse {

    private Long id;

    private String title;

    private List<String> genres;

    private String age;

    private String summary;

    private String broadcastingDay;

    private String broadcastingEpisode;

    private String broadcastingStation;

    private boolean endFlag;

    private Float averageRating;

    private Integer bookmarkCnt;

    private String posterImg;

    private String backdropPath;
}
