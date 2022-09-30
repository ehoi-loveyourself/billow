package com.billow.controller.program;

import com.billow.domain.dto.addtion.RatingRequest;
import com.billow.domain.dto.program.CastResponse;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.dto.program.RandomProgramResponse;
import com.billow.jwt.JwtTokenProvider;
import com.billow.model.service.program.CastService;
import com.billow.model.service.program.ProgramService;
import com.billow.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Api(tags = {"Program API"})
@RestController
@RequestMapping("/program")
public class ProgramController {

    private final ProgramService programService;
    private final CastService castService;

    @ApiOperation(value = "프로그램 검색", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로그램 검색 성공"),
            @ApiResponse(responseCode = "404", description = "검색 내용이 없습니다."),
            @ApiResponse(responseCode = "400", description = "오류가 발생하였습니다.")
    })
    @GetMapping
    public ResponseEntity<Object> searchProgram(@RequestParam(value = "word") String word) {
        log.info("프로그램 검색 API 호출");
        List<ProgramResponse> responses = programService.searchProgram(word);
        log.info("프로그램 검색 성공");
        return ResponseEntity.ok()
                .body(responses);
    }

    @ApiOperation(value = "프로그램 조회", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로그램 조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 프로그램을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "오류가 발생하였습니다.")
    })
    @GetMapping("/{programId}")
    public ResponseEntity<Object> selectProgram(@PathVariable("programId") Long programId) {
        log.info("프로그램 조회 API 호출");
        ProgramResponse responses = programService.selectProgram(programId);
        log.info("프로그램 조회 성공");
        return ResponseEntity.ok()
                .body(responses);
    }

    @ApiOperation(value = "사용자 초기 데이터 수집용 랜덤 프로그램 출력", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "랜덤 프로그램 출력 성공"),
            @ApiResponse(responseCode = "404", description = "해당 유저를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "404", description = "해당 프로그램을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "오류가 발생하였습니다.")
    })
    @GetMapping("/random")
    public ResponseEntity<Object> randomProgram() {
        log.info("사용자 초기 데이터 수집용 랜덤 프로그램 출력 API 호출");
        List<RandomProgramResponse> response = programService.randomProgram();
        log.info("랜덤 프로그램 출력 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @ApiOperation(value = "프로그램 평점 등록", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로그램 평점 등록 성공"),
            @ApiResponse(responseCode = "404", description = "해당 유저를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "404", description = "해당 프로그램을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "오류가 발생하였습니다.")
    })
    @PostMapping("/{programId}")
    public ResponseEntity<Object> postProgramRating환(@RequestHeader("Auth-access") String token, @PathVariable("programId") Long programId, @RequestBody RatingRequest ratingRequest) {
        log.info("프로그램 평점 등록 API 호출");
        Message response = programService.postProgramRating(JwtTokenProvider.getUserId(token), programId, ratingRequest);
        log.info("프로그램 평점 등록 성공");
        return ResponseEntity.ok()
                .body(response);
    }

    @ApiOperation(value = "프로그램 출연진 조회", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로그램 출연진 조회 성공"),
            @ApiResponse(responseCode = "400", description = "오류가 발생하였습니다.")
    })
    @GetMapping("/cast/{programId}")
    public ResponseEntity<Object> selectCast(@PathVariable("programId") Long programId) {
        log.info("프로그램 출연진 조회 API 호출");
        List<CastResponse> response = castService.selectCast(programId);
        log.info("프로그램 출연진 조회 성공");
        return ResponseEntity.ok()
                .body(response);
    }
}