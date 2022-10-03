package com.billow.domain.dto.program;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConditionRecommendRequest {

    private String who;

    private String genre;

    private List<Long> programList;
}