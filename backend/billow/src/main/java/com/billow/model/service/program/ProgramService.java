package com.billow.model.service.program;

import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.program.Program;
import com.billow.model.repository.program.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public List<Program> findAll() {
        return programRepository.findAll();
    }

    public List<ProgramResponse> searchProgram(String word) {
        return programRepository.findByTitleContaining(word)
                .stream()
                .map(program -> ProgramResponse.builder()
                        .title(program.getTitle())
                        .genres(program.getGenreList()
                                .stream()
                                .map(genre -> genre.getGenreInfo().getName())
                                .collect(Collectors.toList()))
                        .age(program.getAge())
                        .summary(program.getSummary())
                        .broadcastingDay(program.getBroadcastingDay())
                        .broadcastingTime(program.getBroadcastingTime())
                        .broadcastingStation(program.getBroadcastingStation())
                        .endFlag(program.isEndFlag())
                        .averageRating(program.getAverageRating())
                        .posterImg(program.getPosterImg())
                        .backdropPath(program.getBackdropPath())
                        .build())
                .collect(Collectors.toList());
    }
}
