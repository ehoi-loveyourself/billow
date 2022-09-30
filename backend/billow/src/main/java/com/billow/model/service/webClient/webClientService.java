package com.billow.model.service.webClient;

import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.program.Genre;
import com.billow.domain.entity.program.Program;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.program.GenreRepository;
import com.billow.model.repository.program.ProgramRepository;
import com.billow.model.repository.user.UserRepository;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class webClientService {

    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private static final String PROGRAM_NOT_FOUND = "해당 프로그램을 찾을 수 없습니다.";

    private final WebClient webClient;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;

    public List<ProgramResponse> userProgramRecommend(Long userId) {
        List<Integer> programIdList = callDjangoApi(userId);
        List<Program> programList = new ArrayList<>();
        List<Genre> genreList;
        for (Integer intProgramId : programIdList) {
            Long programId = Long.valueOf(intProgramId);
            Optional<Program> oProgram = programRepository.findById(programId);
            if (oProgram.isPresent()) {
                Program program = oProgram.get();
                genreList = genreRepository.findByProgramId(programId);
                for (Genre genre : genreList) {
                    program.getGenreList().add(genre);
                }
                programList.add(program);
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
                        .ratingCnt(program.getRatingCnt())
                        .posterImg(program.getPosterImg())
                        .backdropPath(program.getBackdropPath())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Integer> callDjangoApi(Long userId) {
        return webClient.get()
                .uri("db/" + userId + "/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Integer.class)
                .toStream()
                .collect(Collectors.toList());
    }

    public List<ProgramResponse> conditionRecommend(Long userId, Long programId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        programRepository.findById(programId)
                .orElseThrow(() -> new NotFoundException(PROGRAM_NOT_FOUND));
        List<Program> programList = new ArrayList<>();

        List<Long> conditionProgramByDjango = getConditionProgramByDjango(programId);
        List<Genre> genreList = new ArrayList<>();
        for (Long programNum : conditionProgramByDjango) {
            Program program = programRepository.findById(programNum)
                    .orElseThrow(() -> new NotFoundException("프로그램이 없습니다."));
            genreList = genreRepository.findByProgramId(programId);
            for (Genre genre : genreList) {
                program.getGenreList().add(genre);
            }
            programList.add(program);
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
                        .ratingCnt(program.getRatingCnt())
                        .posterImg(program.getPosterImg())
                        .backdropPath(program.getBackdropPath())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Long> getConditionProgramByDjango(Long programId) {
        return webClient.get()
                .uri("db/program/" + programId + "/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Long.class)
                .toStream()
                .collect(Collectors.toList());
    }
}