package com.billow.model.service.user;

import com.billow.domain.entity.user.ProfileImg;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.user.ProfileImgRepository;
import com.billow.model.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileImageService {

    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private final UserRepository userRepository;
    private static final String IMAGE_NOT_FOUND = "해당 프로필 이미지를 찾을 수 없습니다.";

    private final ProfileImgRepository profileImgRepository;

//    public ResponseEntity<Resource> selectProfile(Long userId) throws IOException {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
//
//        ProfileImg profileImg = profileImgRepository.findById(user.getProfileImg().getId())
//                .orElseThrow(() -> new NotFoundException(IMAGE_NOT_FOUND));
//
//        Resource resource = new FileSystemResource("/home/ubuntu/"+profileImg.getSaveFolder() + File.separator + profileImg.getImgName());
//        System.out.println(resource);
//        HttpHeaders header = new HttpHeaders();
//        Path p = Paths.get("/home/ubuntu/" + profileImg.getSaveFolder() + "/" + profileImg.getImgName());
//        header.add("Content-Type", Files.probeContentType(p));
//        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
//    }

    public String initialSelectProfile(Long profileId) throws IOException {
        ProfileImg profileImg = profileImgRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException(IMAGE_NOT_FOUND));
        return profileImg.getUrl();
    }
}