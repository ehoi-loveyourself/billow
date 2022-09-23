package com.billow.domain.dto.addtion;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BroadcastingAlarmResponse {

    private Long id;

    private String title;

    private List<String> genres;

    private String age;

    private String summary;

    private String broadcastingDay;

    private String broadcastingEpisode;

    private String broadcastingStation;

    private boolean endFlag;

    private String firstAirDate;

    private Float averageRating;

    private Integer bookmarkCnt;

    private String posterImg;

    private String backdropPath;

    private Long broadcastingAlarmId;

    private Date dateTime;
}
