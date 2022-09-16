package com.billow.domain.dto.program;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CastResponse {

    Long programId;

    Long posterImgId;

    String actorName;

    String title;

    String summary;

    String genre;

    Integer age;

    Float averageRating;

}
