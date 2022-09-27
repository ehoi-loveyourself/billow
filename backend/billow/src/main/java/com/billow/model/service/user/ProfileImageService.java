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
import org.apache.commons.io.IOUtils;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileImageService {

    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private final UserRepository userRepository;
    private static final String IMAGE_NOT_FOUND = "해당 프로필 이미지를 찾을 수 없습니다.";

    private final ProfileImgRepository profileImgRepository;

    public ResponseEntity<Resource> selectProfile(Long userId) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        ProfileImg profileImg = profileImgRepository.findById(user.getProfileImg().getId())
                .orElseThrow(() -> new NotFoundException(IMAGE_NOT_FOUND));

        Resource resource = new FileSystemResource(profileImg.getSaveFolder() + File.separator + profileImg.getImgName());
        HttpHeaders header = new HttpHeaders();
        Path p = Paths.get("billow/backend/billow/" + profileImg.getSaveFolder() + "/" + profileImg.getImgName());
        header.add("Content-Type", Files.probeContentType(p));
        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

    public ResponseEntity<Resource> initialSelectProfile(Long profileId) throws IOException {
        ProfileImg profileImg = profileImgRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException(IMAGE_NOT_FOUND));

        Resource resource = new FileSystemResource(profileImg.getSaveFolder() + File.separator + profileImg.getImgName());
        HttpHeaders header = new HttpHeaders();
        Path p = Paths.get("billow/backend/billow/" + profileImg.getSaveFolder() + "/" + profileImg.getImgName());
        header.add("Content-Type", Files.probeContentType(p));
        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }
}