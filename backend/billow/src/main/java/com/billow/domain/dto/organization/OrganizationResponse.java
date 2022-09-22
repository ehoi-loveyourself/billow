package com.billow.domain.dto.organization;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrganizationResponse {

    Long id;

    private String title;

    private List<String> genres;

    private String age;

    private boolean endFlag;

    private String firstAirDate;

    private Float averageRating;

    private Integer bookmarkCnt;

    private String posterImg;

    private String backdropPath;

    private Long programOrganizationId;

    private String broadcastingDay;

    private String broadcastingTime;

    private String broadcastingEpisode;

    private String broadcastingAge;

    private String broadcastingRerun;

    private String broadcastingStation;
}
