package com.billow.model.service.program;

import com.billow.domain.dto.program.CastResponse;
import com.billow.domain.dto.program.ProgramIWatchedRequest;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.condition.ConditionProgram;
import com.billow.domain.entity.program.Cast;
import com.billow.domain.entity.program.Program;
import com.billow.domain.entity.user.User;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.addition.RatingRepository;
import com.billow.model.repository.program.CastRepository;
import com.billow.model.repository.program.ProgramRepository;
import com.billow.model.repository.user.UserRepository;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendService {

    private static final String RATING_NOT_FOUND = "사용자의 평점을 찾을 수 없습니다.";
    private static final String ACTOR_NOT_FOUND = "출연진을 찾을 수 없습니다.";
    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private static final String PROGRAM_NOT_FOUND = "해당 프로그램을 찾을 수 없습니다.";

    private final ProgramRepository programRepository;
    private final RatingRepository ratingRepository;
    private final CastRepository castRepository;
    private final UserRepository userRepository;

    public List<Program> recommendOnair() {
        List<Program> programList = programRepository.findAll();
        return null;
    }

    public List<CastResponse> recommendActor(Long userId) {
        List<Rating> ratingList = ratingRepository.findByUser_Id(userId);
        String actor= castRepository.findMaxCountByProgram_Id(ratingList);
        List<Cast> castList = castRepository.findByActorName(userId, actor);
        return castList
                .stream()
                .map(cast -> CastResponse.builder()
                        .programId(cast.getProgram().getId())
                        .posterImg(cast.getProgram().getPosterImg())
                        .actorName(actor)
                        .title(cast.getProgram().getTitle())
                        .genres(cast.getProgram().getGenreList()
                                .stream()
                                .map(genre -> genre.getGenreInfo().getName())
                                .collect(Collectors.toList()))
                        .age(cast.getProgram().getAge())
                        .averageRating(cast.getProgram().getAverageRating())
                        .posterImg(cast.getProgram().getPosterImg())
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

    public Message addProgramIWatched(Long userId, ProgramIWatchedRequest programIWatchedRequest) {
        // user를 찾는다.
        // request에서 상황, 누구, 프로그램을 찾는다.
        // 엔티티 만들어서 save 한다.

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Program program = programRepository.findById(programIWatchedRequest.getProgramId())
                .orElseThrow(() -> new NotFoundException(PROGRAM_NOT_FOUND));

        ConditionProgram conditionProgram = ConditionProgram.builder()
                .user(user)
                .program(program)
                .conditionGenre()
                .conditionWithWhom()
                .build();


        return new Message("사용자가 특정 상황에 봤던 프로그램을 추가하였습니다.");
    }
}
