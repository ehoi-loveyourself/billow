package com.billow.model.service.addtion;

import com.billow.domain.dto.addtion.BroadcastingAlarmResponse;
import com.billow.domain.dto.addtion.ReviewRequest;
import com.billow.domain.dto.addtion.ReviewResponse;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.addition.BroadcastingAlarm;
import com.billow.domain.entity.addition.Review;
import com.billow.domain.entity.program.Program;
import com.billow.domain.entity.user.User;
import com.billow.exception.BadRequestException;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.addition.BroadcastingAlarmRepository;
import com.billow.model.repository.addition.ReviewRepository;
import com.billow.model.repository.program.ProgramRepository;
import com.billow.model.repository.user.UserRepository;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BroadcastingAlarmService {

    private static final String PROGRAM_NOT_FOUND = "해당 프로그램을 찾을 수 없습니다.";
    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private static final String REVIEW_NOT_FOUND = "해당 리뷰를 찾을 수 없습니다.";
    private static final String BAD_REQUEST = "잘못된 접근입니다.";

    private final BroadcastingAlarmRepository broadcastingAlarmRepository;

    public List<BroadcastingAlarmResponse> selectAlarm(Long userId) {
        List<BroadcastingAlarm> broadcastingAlarmList = broadcastingAlarmRepository.findByUser_Id(userId);
        System.out.println(broadcastingAlarmList);
        return broadcastingAlarmList
                .stream()
                .map(alarm -> BroadcastingAlarmResponse.builder()
                        .id(alarm.getProgram().getId())
                        .title(alarm.getProgram().getTitle())
                        .genres(alarm.getProgram().getGenreList()
                                .stream()
                                .map(genre -> genre.getGenreInfo().getName())
                                .collect(Collectors.toList()))
                        .age(alarm.getProgram().getAge())
                        .summary(alarm.getProgram().getSummary())
                        .broadcastingDay(alarm.getProgram().getBroadcastingDay())
                        .broadcastingEpisode(alarm.getProgram().getBroadcastingEpisode())
                        .broadcastingStation(alarm.getProgram().getBroadcastingStation())
                        .endFlag(alarm.getProgram().isEndFlag())
                        .averageRating(alarm.getProgram().getAverageRating())
                        .bookmarkCnt(alarm.getProgram().getBookmarkCnt())
                        .posterImg(alarm.getProgram().getPosterImg())
                        .backdropPath(alarm.getProgram().getBackdropPath())
                        .broadcastingAlarmId(alarm.getId())
                        .dateTime(alarm.getDateTime())
                        .build())
                .collect(Collectors.toList());
    }
}