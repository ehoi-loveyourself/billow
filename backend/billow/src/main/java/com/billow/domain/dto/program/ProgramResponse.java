package com.billow.domain.dto.program;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgramResponse {

    private String title;

    private List<String> genres;

    private Integer age;

    private String summary;

    private String broadcastingDay;

    private String broadcastingTime;

    private String broadcastingStation;

    private boolean endFlag;

    private Float averageRating;

    private String posterImg;
}
