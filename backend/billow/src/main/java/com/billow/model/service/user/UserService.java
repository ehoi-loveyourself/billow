package com.billow.model.service.user;

import com.billow.domain.dto.addtion.RatingRequest;
import com.billow.domain.dto.addtion.RatingResponse;
import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.user.User;
import com.billow.exception.NotFoundException;
import com.billow.exception.WrongAccessException;
import com.billow.model.repository.addition.RatingRepository;
import com.billow.model.repository.user.UserRepository;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private static final String RATING_NOT_FOUND = "해당 평점을 찾을 수 없습니다.";
    private static final String WRONG_ACCESS = "잘못된 접근입니다.";

    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    public List<RatingResponse> selectRating(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        return user.getRatings()
                .stream()
                .map(rating -> RatingResponse.builder()
                        .title(rating.getProgram().getTitle())
                        .score(rating.getScore())
                        .build())
                .collect(Collectors.toList());
    }

    public Message updateRating(Long userId, Long ratingId, RatingRequest ratingRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new NotFoundException(RATING_NOT_FOUND));

        if (!user.equals(rating.getUser())) {
            throw new WrongAccessException(WRONG_ACCESS);
        }

        rating.updateRating(ratingRequest.getScore());
        ratingRepository.save(rating);

        /*
         * 질문 !
         * 연관관계 매핑에서 cascade
         */
        return new Message("회원님의 평점내역 수정에 성공하였습니다.");
    }

    public Message deleteRating(Long userId, Long ratingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new NotFoundException(RATING_NOT_FOUND));

        if (!user.equals(rating.getUser())) {
            throw new WrongAccessException(WRONG_ACCESS);
        }

        ratingRepository.delete(rating);

        return new Message("회원님의 평점내역 삭제에 성공하였습니다.");
    }
}
