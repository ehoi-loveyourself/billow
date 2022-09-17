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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProgramService {

    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private static final String PROGRAM_NOT_FOUND = "해당 프로그램을 찾을 수 없습니다.";

    private final ProgramRepository programRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

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

    public Message postProgramRating(Long userId, Long programId, RatingRequest ratingRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new NotFoundException(PROGRAM_NOT_FOUND));

        Rating rating = Rating.builder()
                .user(user)
                .program(program)
                .score(ratingRequest.getScore())
                .build();
        ratingRepository.save(rating);

//        program.updateAverageRatingByPost(ratingRequest.getScore());

        // 프로그램 평점 평균에 적용을 해야해
        // 평점 삭제했을 때도 적용 시켜야 한다!

        return new Message("프로그램 평점 등록에 성공하였습니다.");
    }
}
