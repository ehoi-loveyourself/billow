package com.billow.model.service.webClient;

import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.program.Genre;
import com.billow.domain.entity.program.Program;
import com.billow.model.repository.program.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class webClientService {

    private final WebClient webClient;

    private final GenreRepository genreRepository;

    public List<Program> callDjangoApi(Long userId) {
        return webClient.get()
                .uri("db/" + userId + "/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Program.class)
                .toStream()
                .collect(Collectors.toList());
    }

    public List<ProgramResponse> userProgramRecommand(Long userId) {
        List<Program> programList = callDjangoApi(userId);
        List<Genre> genreList = new ArrayList<>();
        for (Program program : programList) {
            Long programId = program.getId();
            genreList = genreRepository.findByProgramId(programId);
            for (Genre genre : genreList) {
                program.getGenreList().add(genre);
            }
        }
        return programList
                .stream()
                .map(program -> ProgramResponse.builder()
                        .id(program.getId())
                        .title(program.getTitle())
                        .genres(program.getGenreList()
                                .stream()
                                .map(genre -> genre.getGenreInfo().getName())
                                .collect(Collectors.toList()))
                        .age(program.getAge())
                        .summary(program.getSummary())
                        .broadcastingDay(program.getBroadcastingDay())
                        .broadcastingEpisode(program.getBroadcastingEpisode())
                        .broadcastingStation(program.getBroadcastingStation())
                        .endFlag(program.isEndFlag())
                        .firstAirDate(DateFormat.getDateInstance(DateFormat.LONG).format(program.getFirstAirDate()))
                        .averageRating(program.getAverageRating())
                        .bookmarkCnt(program.getBookmarkCnt())
                        .posterImg(program.getPosterImg())
                        .backdropPath(program.getBackdropPath())
                        .build())
                .collect(Collectors.toList());
    }
}
