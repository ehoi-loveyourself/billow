package com.billow.model.service.program;

import com.billow.domain.dto.organization.OrganizationResponse;
import com.billow.domain.dto.program.CastResponse;
import com.billow.domain.dto.program.OttResponse;
import com.billow.domain.dto.program.ProgramIWatchedRequest;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.condition.ConditionGenre;
import com.billow.domain.entity.condition.ConditionProgram;
import com.billow.domain.entity.condition.ConditionWithWhom;
import com.billow.domain.entity.organization.ProgramOrganization;
import com.billow.domain.entity.program.Cast;
import com.billow.domain.entity.program.GenderAgeViewer;
import com.billow.domain.entity.program.Program;
import com.billow.domain.entity.user.User;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.addition.RatingRepository;
import com.billow.model.repository.condition.ConditionGenreRepository;
import com.billow.model.repository.condition.ConditionProgramRepository;
import com.billow.model.repository.condition.ConditionWithWhomRepository;
import com.billow.model.repository.organization.ProgramOrganizationRepository;
import com.billow.model.repository.program.CastRepository;
import com.billow.model.repository.program.GenderAgeViewerRepository;
import com.billow.model.repository.program.ProgramRepository;
import com.billow.model.repository.user.UserRepository;
import com.billow.util.DateUtil;
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
    private final ProgramOrganizationRepository programOrganizationRepository;

    public List<OrganizationResponse> recommendOnair() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -2);
        Date now = new Date();
        Date date = new Date(calendar.getTimeInMillis());
        List<ProgramOrganization> programOrganizationList = programOrganizationRepository.findByBroadcastingTimeBetween(date, now);

        return programOrganizationList
                .stream()
                .map(organization -> OrganizationResponse.builder()
                        .id(organization.getProgram().getId())
                        .title(organization.getProgram().getTitle())
                        .genres(organization.getProgram().getGenreList()
                                .stream()
                                .map(genre -> genre.getGenreInfo().getName())
                                .collect(Collectors.toList()))
                        .age(organization.getProgram().getAge())
                        .endFlag(organization.getProgram().isEndFlag())
                        .firstAirDate(DateFormat.getDateInstance(DateFormat.LONG).format(organization.getProgram().getFirstAirDate()))
                        .averageRating(organization.getProgram().getAverageRating())
                        .bookmarkCnt(organization.getProgram().getBookmarkCnt())
                        .posterImg(organization.getProgram().getPosterImg())
                        .backdropPath(organization.getProgram().getBackdropPath())
                        .programOrganizationId(organization.getId())
                        .broadcastingDay(DateUtil.toYYYY_MM_DD(organization.getBroadcastingTime()) + " " + organization.getBroadcastingDay())
                        .broadcastingTime(DateUtil.toHH_mm(organization.getBroadcastingTime()))
                        .broadcastingEpisode(organization.getBroadcastingEpisode())
                        .broadcastingAge(organization.getBroadcastingAge())
                        .broadcastingRerun(organization.getBroadcastingRerun())
                        .broadcastingStation(organization.getBroadcastingStation())
                        .otts(organization.getProgram().getOttList()
                                .stream()
                                .map(ott -> OttResponse.builder()
                                        .name(ott.getOttInfo().getName())
                                        .url(ott.getOttInfo().getUrl())
                                        .imgUrl(ott.getOttInfo().getImgUrl())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    public List<CastResponse> recommendActor(Long userId) {
        List<Rating> ratingList = ratingRepository.findByUser_Id(userId);
        String actor = castRepository.findMaxCountByProgram_Id(ratingList);
        List<Cast> castList = castRepository.findByActorName(userId, actor);
        return castList
                .stream()
                .map(cast -> CastResponse.builder()
                        .id(cast.getProgram().getId())
                        .actorName(actor)
                        .title(cast.getProgram().getTitle())
                        .genres(cast.getProgram().getGenreList()
                                .stream()
                                .map(genre -> genre.getGenreInfo().getName())
                                .collect(Collectors.toList()))
                        .age(cast.getProgram().getAge())
                        .summary(cast.getProgram().getSummary())
                        .broadcastingDay(cast.getProgram().getBroadcastingDay())
                        .broadcastingEpisode(cast.getProgram().getBroadcastingEpisode())
                        .broadcastingStation(cast.getProgram().getBroadcastingStation())
                        .endFlag(cast.getProgram().isEndFlag())
                        .firstAirDate(DateFormat.getDateInstance(DateFormat.LONG).format(cast.getProgram().getFirstAirDate()))
                        .averageRating(cast.getProgram().getAverageRating())
                        .bookmarkCnt(cast.getProgram().getBookmarkCnt())
                        .ratingCnt(cast.getProgram().getRatingCnt())
                        .posterImg(cast.getProgram().getPosterImg())
                        .backdropPath(cast.getProgram().getBackdropPath())
                        .otts(cast.getProgram().getOttList()
                                .stream()
                                .map(ott -> OttResponse.builder()
                                        .name(ott.getOttInfo().getName())
                                        .url(ott.getOttInfo().getUrl())
                                        .imgUrl(ott.getOttInfo().getImgUrl())
                                        .build())
                                .collect(Collectors.toList()))
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
                        .firstAirDate(DateFormat.getDateInstance(DateFormat.LONG).format(program.getFirstAirDate()))
                        .averageRating(program.getAverageRating())
                        .bookmarkCnt(program.getBookmarkCnt())
                        .ratingCnt(program.getRatingCnt())
                        .posterImg(program.getPosterImg())
                        .backdropPath(program.getBackdropPath())
                        .otts(program.getOttList()
                                .stream()
                                .map(ott -> OttResponse.builder()
                                        .name(ott.getOttInfo().getName())
                                        .url(ott.getOttInfo().getUrl())
                                        .imgUrl(ott.getOttInfo().getImgUrl())
                                        .build())
                                .collect(Collectors.toList()))
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
                        .ratingCnt(program.getRatingCnt())
                        .bookmarkCnt(program.getBookmarkCnt())
                        .posterImg(program.getPosterImg())
                        .backdropPath(program.getBackdropPath())
                        .otts(program.getOttList()
                                .stream()
                                .map(ott -> OttResponse.builder()
                                        .name(ott.getOttInfo().getName())
                                        .url(ott.getOttInfo().getUrl())
                                        .imgUrl(ott.getOttInfo().getImgUrl())
                                        .build())
                                .collect(Collectors.toList()))
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
                        .ratingCnt(program.getRatingCnt())
                        .bookmarkCnt(program.getBookmarkCnt())
                        .posterImg(program.getPosterImg())
                        .backdropPath(program.getBackdropPath())
                        .otts(program.getOttList()
                                .stream()
                                .map(ott -> OttResponse.builder()
                                        .name(ott.getOttInfo().getName())
                                        .url(ott.getOttInfo().getUrl())
                                        .imgUrl(ott.getOttInfo().getImgUrl())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}