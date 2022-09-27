package com.billow.model.service.program;

import com.billow.domain.dto.addtion.RatingRequest;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.program.Program;
import com.billow.domain.entity.user.User;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.addition.RatingRepository;
import com.billow.model.repository.program.ProgramRepository;
import com.billow.model.repository.user.UserRepository;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProgramService {

    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private static final String PROGRAM_NOT_FOUND = "해당 프로그램을 찾을 수 없습니다.";
    private static final String NO_SEARCH = "검색 내용이 없습니다.";

    private final ProgramRepository programRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    public List<Program> findAll() {
        return programRepository.findAll();
    }

    public List<ProgramResponse> searchProgram(String word) {
        List<Program> list = programRepository.findByTitleContaining(word);
        if (list.size() == 0) {
            throw new NotFoundException(NO_SEARCH);
        }
        return list
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
                        .ratingCnt(program.getRatingCnt())
                        .posterImg(program.getPosterImg())
                        .backdropPath(program.getBackdropPath())
                        .build())
                .collect(Collectors.toList());
    }

    public void save(Program program) {
        programRepository.save(program);
    }

    public Message postProgramRating(Long userId, Long programId, RatingRequest ratingRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new NotFoundException(PROGRAM_NOT_FOUND));

        program.updateAverageRatingByPost(ratingRequest.getScore());

        Rating rating = Rating.builder()
                .user(user)
                .program(program)
                .score(ratingRequest.getScore())
                .build();
        ratingRepository.save(rating);

        return new Message("프로그램 평점 등록에 성공하였습니다.");
    }

    public List<ProgramResponse> randomProgram(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        int programCnt = (int) programRepository.count();

        Random r = new Random();
        int[] random = new int[50];
        for (int i = 0; i < 50; i++) {
            random[i] = r.nextInt(programCnt);
        }

        log.info("프로그램 총 개수 : {}", programCnt);
        log.info("랜덤 프로그램 id : {}", Arrays.toString(random));

        List<ProgramResponse> responses = new ArrayList<>();
        for (int i = 0; i < random.length; i++) {
            Program program = programRepository.findById(Long.valueOf(random[i]))
                    .orElseThrow(() -> new NotFoundException(PROGRAM_NOT_FOUND));

            responses.add(ProgramResponse.builder()
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
                    .ratingCnt(program.getRatingCnt())
                    .posterImg(program.getPosterImg())
                    .backdropPath(program.getBackdropPath())
                    .build());
        }
        return responses;
    }

    public ProgramResponse selectProgram(Long programId) {
        Program program
                = programRepository.findById(programId)
                .orElseThrow(() -> new NotFoundException(PROGRAM_NOT_FOUND));

        return ProgramResponse.builder()
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
                .averageRating(Float.valueOf(String.format("%.1f", program.getAverageRating())))
                .bookmarkCnt(program.getBookmarkCnt())
                .posterImg(program.getPosterImg())
                .backdropPath(program.getBackdropPath())
                .build();
    }
}
