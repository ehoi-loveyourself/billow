package com.billow.model.service.addtion;

import com.billow.domain.dto.addtion.BroadcastingAlarmResponse;
import com.billow.domain.dto.addtion.ReviewRequest;
import com.billow.domain.dto.addtion.ReviewResponse;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.addition.BroadcastingAlarm;
import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.addition.Review;
import com.billow.domain.entity.organization.ProgramOrganization;
import com.billow.domain.entity.program.Program;
import com.billow.domain.entity.user.User;
import com.billow.exception.BadRequestException;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.addition.BroadcastingAlarmRepository;
import com.billow.model.repository.addition.ReviewRepository;
import com.billow.model.repository.program.ProgramRepository;
import com.billow.model.repository.user.UserRepository;
import com.billow.util.DateUtil;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BroadcastingAlarmService {

    private static final String ALARM_NOT_FOUND = "등록되지 않은 알람입니다.";

    private final BroadcastingAlarmRepository broadcastingAlarmRepository;

    public List<BroadcastingAlarmResponse> selectAlarm(Long userId) {
        List<BroadcastingAlarm> broadcastingAlarmList = broadcastingAlarmRepository.findByUser_Id(userId);
        return broadcastingAlarmList
                .stream()
                .map(alarm -> BroadcastingAlarmResponse.builder()
                        .id(alarm.getProgramOrganization().getProgram().getId())
                        .title(alarm.getProgramOrganization().getProgram().getTitle())
                        .genres(alarm.getProgramOrganization().getProgram().getGenreList()
                                .stream()
                                .map(genre -> genre.getGenreInfo().getName())
                                .collect(Collectors.toList()))
                        .posterImg(alarm.getProgramOrganization().getProgram().getPosterImg())
                        .broadcastingAlarmId(alarm.getId())
                        .alarmDay(DateUtil.toYYYY_MM_DD(alarm.getProgramOrganization().getBroadcastingTime()) + " " + alarm.getProgramOrganization().getBroadcastingDay())
                        .alarmTime(DateUtil.toHH_mm(alarm.getProgramOrganization().getBroadcastingTime()))
                        .alarmStation(alarm.getProgramOrganization().getBroadcastingStation())
                        .build())
                .collect(Collectors.toList());
    }

    public Message postAlarm(Long userId, Long programOrganizationId) {
        Optional<BroadcastingAlarm> findBroadcastingAlarm = broadcastingAlarmRepository.findByProgramOrganization_Id(programOrganizationId);
        if (!findBroadcastingAlarm.isEmpty()) {
            return new Message("이미 등록된 알림입니다.");
        }
        ProgramOrganization programOrganization = ProgramOrganization.builder().id(programOrganizationId).build();
        User user = User.builder().id(userId).build();
        BroadcastingAlarm broadcastingAlarm = BroadcastingAlarm.builder()
                .programOrganization(programOrganization)
                .user(user)
                .build();
        broadcastingAlarmRepository.save(broadcastingAlarm);
        return new Message("방영 알림 등록에 성공하였습니다.");
    }

    public Message deleteAlarm(Long alarmId) {
        BroadcastingAlarm findBroadcastingAlarm = broadcastingAlarmRepository.findById(alarmId)
                .orElseThrow(() -> new NotFoundException(ALARM_NOT_FOUND));
        broadcastingAlarmRepository.delete(findBroadcastingAlarm);
        return new Message("방영 알림 삭제에 성공하였습니다.");
    }
}