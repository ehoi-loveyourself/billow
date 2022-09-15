package com.billow.domain.dto.program;

import com.billow.domain.entity.program.PosterImg;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgramResponse {

    private String title;
    private String genre;
    private Integer age;
    private String summary;
    private String broadcastingDay;
    private String broadcastingTime;
    private String broadcastingStation;
    private boolean endFlag;
    private Float averageRating;
    private PosterImg posterImg;
}
