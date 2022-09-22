package com.billow.model.service.program;

import com.billow.domain.dto.program.CastResponse;
import com.billow.domain.dto.program.ProgramIWatchedRequest;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.condition.ConditionGenre;
import com.billow.domain.entity.condition.ConditionProgram;
import com.billow.domain.entity.condition.ConditionWithWhom;
import com.billow.domain.entity.program.Cast;
import com.billow.domain.entity.program.GenderAgeViewer;
import com.billow.domain.entity.program.Program;
import com.billow.domain.entity.user.User;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.addition.RatingRepository;
import com.billow.model.repository.condition.ConditionGenreRepository;
import com.billow.model.repository.condition.ConditionProgramRepository;
import com.billow.model.repository.condition.ConditionWithWhomRepository;
import com.billow.model.repository.program.CastRepository;
import com.billow.model.repository.program.GenderAgeViewerRepository;
import com.billow.model.repository.program.ProgramRepository;
import com.billow.model.repository.user.UserRepository;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private final ConditionGenreRepository conditionGenreRepository;
    private final ConditionWithWhomRepository conditionWithWhomRepository;
    private final ConditionProgramRepository conditionProgramRepository;
    private final GenderAgeViewerRepository genderAgeViewerRepository;

    public List<Program> recommendOnair() {
        List<Program> programList = programRepository.findAll();
        return null;
    }

    public List<CastResponse> recommendActor(Long userId) {
        List<Rating> ratingList = ratingRepository.findByUser_Id(userId);
        String actor = castRepository.findMaxCountByProgram_Id(ratingList);
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
                        .averageRating(program.getAverageRating())
                        .bookmarkCnt(program.getBookmarkCnt())
                        .posterImg(program.getPosterImg())
                        .backdropPath(program.getBackdropPath())
                        .build())
                .collect(Collectors.toList());
    }

    public Message addProgramIWatched(Long userId, ProgramIWatchedRequest programIWatchedRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Program program = programRepository.findById(programIWatchedRequest.getProgramId())
                .orElseThrow(() -> new NotFoundException(PROGRAM_NOT_FOUND));
        ConditionGenre conditionGenre = conditionGenreRepository.findByGenre(programIWatchedRequest.getGenre());
        ConditionWithWhom conditionWithWhom = conditionWithWhomRepository.findByWho(programIWatchedRequest.getWho());

        ConditionProgram conditionProgram = ConditionProgram.builder()
                .user(user)
                .program(program)
                .conditionGenre(conditionGenre)
                .conditionWithWhom(conditionWithWhom)
                .build();
        conditionProgramRepository.save(conditionProgram);

        return new Message("사용자가 특정 상황에 봤던 프로그램을 추가하였습니다.");
    }

    public List<ProgramResponse> recommendNew() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);

        Date date = new Date(calendar.getTimeInMillis());
        List<Program> programList = programRepository.findByFirstAirDateAfterOrderByFirstAirDateDesc(date);

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
    
    public List<ProgramResponse> recommendGenderAge(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        List<GenderAgeViewer> genderAgeViewerList = genderAgeViewerRepository.findTop5ByGenderAge(user.getAge(), user.getGender());
        List<Program> programResponseList = programRepository.findGenderAgeRecommend(userId, user.getAge(), user.getGender(), genderAgeViewerList.get(0).getProgram().getId(), genderAgeViewerList.get(1).getProgram().getId(), genderAgeViewerList.get(2).getProgram().getId(), genderAgeViewerList.get(3).getProgram().getId(), genderAgeViewerList.get(4).getProgram().getId());

        return programResponseList
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