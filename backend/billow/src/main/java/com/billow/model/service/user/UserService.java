package com.billow.model.service.user;

import com.billow.domain.dto.addtion.RatingRequest;
import com.billow.domain.dto.addtion.RatingResponse;
import com.billow.domain.dto.user.*;
import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.user.ProfileImg;
import com.billow.domain.entity.user.Region;
import com.billow.domain.entity.user.TvCarrier;
import com.billow.domain.entity.user.User;
import com.billow.exception.BadRequestException;
import com.billow.exception.DuplicationException;
import com.billow.exception.NotFoundException;
import com.billow.exception.WrongFormException;
import com.billow.model.repository.addition.RatingRepository;
import com.billow.model.repository.user.ProfileImgRepository;
import com.billow.model.repository.user.RegionRepository;
import com.billow.model.repository.user.TvCarrierRepository;
import com.billow.model.repository.user.UserRepository;
import com.billow.util.JwtUtil;
import com.billow.util.KakaoOAuth2;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private static final String EMAIL_NOT_FOUND = "이메일 수집에 동의해주세요.";
    private static final String NICKNAME_DUPLICATED = "이미 등록된 닉네임입니다.";
    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private static final String RATING_NOT_FOUND = "해당 평점을 찾을 수 없습니다.";
    private static final String BAD_REQUEST = "잘못된 요청입니다.";
    private static final String TOKEN_NOT_VALID = "토큰 정보가 올바르지 않습니다.";
    private static final String PROFILE_IMG_NOT_FOUND = "프로필 이미지를 찾을 수 없습니다.";
    private static final String PROFILE_IMG_ABSOLUTE_PATH = "img/profile_img/";


    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final RegionRepository regionRepository;
    private final TvCarrierRepository tvCarrierRepository;
    private final ProfileImgRepository profileImgRepository;
    private final KakaoOAuth2 kakaoOAuth2;

    public UserResponse selectUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        String profileImgUrl = PROFILE_IMG_ABSOLUTE_PATH + user.getProfileImg().getImgName();
        // url 설정 이렇게 하면 되나요..?
        // "img/profile_img/1.png" 이런 식으로 반환하는데 뭔가 아닌 거 같은데요.. 핳

        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .nickName(user.getNickName())
                .gender(user.getGender())
                .age(user.getAge())
                .region(user.getRegion().getRegion())
                .tvCarrier(user.getTvCarrier().getCompany())
                .profileImgUrl(profileImgUrl)
                .mobile(user.getMobile())
                .build();
    }

    public LoginResponse kakaoLogin(String code, HttpServletResponse httpServletResponse) throws ParseException {
        User kakaoUser = kakaoOAuth2.getUserInfo(code);
        log.info(kakaoUser.toString());

        if (kakaoUser.getEmail() == null) {
            throw new NotFoundException(EMAIL_NOT_FOUND);
        } else {
            User user = userRepository.findByEmail(kakaoUser.getEmail());
            if (user == null) {
                user = new User(kakaoUser.getEmail(), kakaoUser.getNickName());
                userRepository.save(user);
            }
            String authToken = JwtUtil.createAuthToken(user.getId(), user.getEmail(), user.getName());
            String refreshToken = JwtUtil.createRefreshToken();

            Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
            refreshCookie.setMaxAge(60 * 60 * 24); // 하루
            refreshCookie.setSecure(true);
            refreshCookie.setHttpOnly(true);
            refreshCookie.setPath("/");

            httpServletResponse.addCookie(refreshCookie);

            saveRefreshToken(user.getEmail(), refreshToken);

            return LoginResponse.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .nickName(user.getNickName())
                    .authToken(authToken)
                    .build();
        }
    }

    public Message validateNickname(String nickname) {
        if (userRepository.existsByNickName(nickname)) {
            throw new DuplicationException(NICKNAME_DUPLICATED);
        }
        return new Message("사용 가능한 닉네임입니다.");
   }

    public Message signUp(SignUpRequest signUpRequest) {
        User user = userRepository.findByEmail(signUpRequest.getEmail());
        if (!user.getName().equals(signUpRequest.getName())) {
            throw new BadRequestException(BAD_REQUEST);
        }
        // 지역을 찾아서 객체로 넣어줘야해
        Region region = regionRepository.findByRegion(signUpRequest.getRegion());
        // 통신사도 찾아서 객체로 넣어줘야 하고
        TvCarrier tvCarrier = tvCarrierRepository.findByCompany(signUpRequest.getTvCarrier());
        // 프로필 이미지도 번호를 찾아서
        ProfileImg profileImg = profileImgRepository.findById(signUpRequest.getProfileImgId())
                .orElseThrow(() -> new NotFoundException(PROFILE_IMG_NOT_FOUND));

        user.postProfile(
                signUpRequest.getNickName(),
                signUpRequest.getGender(),
                signUpRequest.getAge(),
                region,
                tvCarrier,
                profileImg,
                signUpRequest.getMobile()
        );
        userRepository.save(user);
        return new Message("회원가입이 성공하였습니다.");
    }

    public AuthTokenResponse refresh(String email, String refreshToken) {
        User user = userRepository.findByEmail(email);
        // 데이터베이스에 있는 리프레시 토큰과 같은지 판단
        if (!user.getRefreshToken().equals(refreshToken)) {
            throw new WrongFormException(TOKEN_NOT_VALID);
        }
        return AuthTokenResponse.builder()
                .AuthToken(JwtUtil.createAuthToken(user.getId(), user.getEmail(), user.getName()))
                .build();
    }

    public Message logout(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user.deleteRefreshToken();
        userRepository.save(user);
        return new Message("로그아웃에 성공하였습니다.");
    }

    public Message updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Region region = regionRepository.findByRegion(userUpdateRequest.getRegion());
        TvCarrier tvCarrier = tvCarrierRepository.findByCompany(userUpdateRequest.getTvCarrier());
        ProfileImg profileImg = profileImgRepository.findById(userUpdateRequest.getProfileImgId())
                .orElseThrow(() -> new NotFoundException(PROFILE_IMG_NOT_FOUND));

        user.postProfile(
                userUpdateRequest.getNickName(),
                userUpdateRequest.getGender(),
                userUpdateRequest.getAge(),
                region,
                tvCarrier,
                profileImg,
                userUpdateRequest.getMobile()
        );
        userRepository.save(user);
        return new Message("회원정보 수정에 성공하였습니다.");
    }

    public Message deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        userRepository.delete(user);
        return new Message("회원 탈퇴에 성공했습니다.");
    }

    public List<RatingResponse> selectRating(Long userId) {
        return ratingRepository.findByUser_Id(userId)
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
            throw new BadRequestException(BAD_REQUEST);
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
            throw new BadRequestException(BAD_REQUEST);
        }
        rating.getProgram().updateAverageRatingByDelete(rating.getScore());
        ratingRepository.delete(rating);

        return new Message("회원님의 평점내역 삭제에 성공하였습니다.");
    }

    private void saveRefreshToken(String email, String refreshToken) {
        User user = userRepository.findByEmail(email);
        user.saveRefreshToken(refreshToken);
        userRepository.save(user);
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
}
