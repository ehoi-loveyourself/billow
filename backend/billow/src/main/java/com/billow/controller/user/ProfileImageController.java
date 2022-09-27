package com.billow.controller.user;

import com.billow.domain.dto.addtion.RatingRequest;
import com.billow.domain.dto.addtion.RatingResponse;
import com.billow.domain.dto.user.*;
import com.billow.exception.BadRequestException;
import com.billow.model.service.user.ProfileImageService;
import com.billow.util.JwtUtil;
import com.billow.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Api(tags = {"Profile Image API"})
@RestController
@RequestMapping("/profile")
public class ProfileImageController {

    private static final String PROFILE_ERROR = "프로필 조회 중 오류가 발생하였습니다.";

    private final ProfileImageService profileImageService;

//    @ApiOperation(value = "프로필 조회", response = Object.class)
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "프로필 조회 성공")})
//    @GetMapping("/{userId}")
//    public ResponseEntity<Resource> selectProfile(@PathVariable("userId") Long userId) {
//        try {
//            log.info("프로필 조회 API 호출");
//            ResponseEntity<Resource> resource = profileImageService.selectProfile(1L);
//            log.info("프로필 조회 성공");
//            return resource;
//        } catch (Exception e) {
//            throw new BadRequestException(PROFILE_ERROR);
//        }
//    }
//
    @ApiOperation(value = "초기 프로필 조회", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "초기 프로필 조회 성공")})
    @GetMapping("/initial/{profileId}")
    public ResponseEntity<String> initialSelectProfile(@PathVariable("profileId") Long profileId) {
        try {
            log.info("초기 프로필 조회 API 호출");
            String resource = profileImageService.initialSelectProfile(profileId);
            log.info("초기 프로필 조회 성공");
            return ResponseEntity.ok()
                    .body(resource);
        } catch (Exception e) {
            throw new BadRequestException(PROFILE_ERROR);
        }
    }
}