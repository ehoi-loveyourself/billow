package com.billow.domain.dto.program;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CastResponse {

    Long programId;

    String posterImg;

    String actorName;

    String title;

    String summary;

    List<String> genres;

    String age;

    Float averageRating;
}
