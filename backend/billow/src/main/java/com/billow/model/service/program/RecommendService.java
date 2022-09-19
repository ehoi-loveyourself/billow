package com.billow.model.service.program;

import com.billow.domain.dto.program.CastResponse;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.program.Cast;
import com.billow.domain.entity.program.Program;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.addition.RatingRepository;
import com.billow.model.repository.program.CastRepository;
import com.billow.model.repository.program.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendService {

    private static final String RATING_NOT_FOUND = "사용자의 평점을 찾을 수 없습니다.";
    private static final String ACTOR_NOT_FOUND = "출연진을 찾을 수 없습니다.";

    private final ProgramRepository programRepository;
    private final RatingRepository ratingRepository;
    private final CastRepository castRepository;

    public List<Program> recommendOnair() {
        List<Program> programList = programRepository.findAll();
        return null;
    }

    public List<CastResponse> recommendActor(Long userId) {
        List<Rating> ratingList = ratingRepository.findByUser_Id(userId);
        List<String> actorList = castRepository.findMaxCountByProgram_Id(ratingList.get(0).getProgram().getId(), ratingList.get(1).getProgram().getId(), ratingList.get(2).getProgram().getId(), ratingList.get(3).getProgram().getId(), ratingList.get(4).getProgram().getId());
        List<Cast> castList = castRepository.findByActorName(actorList.get(0))
                .orElseThrow(() -> new NotFoundException(ACTOR_NOT_FOUND));

        return castList
                .stream()
                .map(cast -> CastResponse.builder()
                        .programId(cast.getProgram().getId())
                        .posterImg(cast.getProgram().getPosterImg())
                        .actorName(actorList.get(0))
                        .title(cast.getProgram().getTitle())
                        .age(cast.getProgram().getAge())
                        .averageRating(cast.getProgram().getAverageRating())
                        .genres(cast.getProgram().getGenreList()
                                .stream()
                                .map(genre -> genre.getGenreInfo().getName())
                                .collect(Collectors.toList()))
                        .summary(cast.getProgram().getSummary())
                        .build())
                .collect(Collectors.toList());
    }

    public List<ProgramResponse> recommendPopular() {
        return programRepository.findPopularProgram()
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
                        .broadcastingEpisode(program.getBroadcastingEpisode())
                        .broadcastingStation(program.getBroadcastingStation())
                        .endFlag(program.isEndFlag())
                        .averageRating(program.getAverageRating())
                        .bookmarkCnt(program.getBookmarkCnt())
                        .posterImg(program.getPosterImg())
                        .backdropPath(program.getBackdropPath())
                        .build())
                .collect(Collectors.toList());
    }
}
