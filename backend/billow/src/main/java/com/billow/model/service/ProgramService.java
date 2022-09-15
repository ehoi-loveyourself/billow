package com.billow.model.service;

import com.billow.domain.dto.program.ProgramResponse;
import com.billow.model.repository.program.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public List<ProgramResponse> searchProgram(String word) {
        return programRepository.findByTitleContaining(word)
                .stream()
                .map(program -> ProgramResponse.builder()
                        .title(program.getTitle())
                        .genre(program.getGenre())
                        .age(program.getAge())
                        .summary(program.getSummary())
                        .broadcastingDay(program.getBroadcastingDay())
                        .broadcastingTime(program.getBroadcastingTime())
                        .broadcastingStation(program.getBroadcastingStation())
                        .endFlag(program.isEndFlag())
                        .averageRating(program.getAverageRating())
                        .posterImg(program.getPosterImg())
                        .build())
                .collect(Collectors.toList());
    }
}
