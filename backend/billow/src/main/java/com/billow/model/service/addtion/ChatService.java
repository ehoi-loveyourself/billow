package com.billow.model.service.addtion;

import com.billow.domain.dto.addtion.ChatRequest;
import com.billow.domain.dto.addtion.ChatResponse;
import com.billow.domain.entity.addition.Chat;
import com.billow.domain.entity.program.Program;
import com.billow.domain.entity.user.User;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.addition.ChatRepository;
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
public class ChatService {

    private static final String PROGRAM_NOT_FOUND = "해당 프로그램을 찾을 수 없습니다.";
    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;

    public List<ChatResponse> selectChat(Long programId) {
        return chatRepository.findByProgramId(programId)
                .stream()
                .map(chat -> ChatResponse.builder()
                        .userNickName(chat.getUser().getNickName())
                        .content(chat.getContent())
                        .regDateTime(chat.getDateTime())
                        .build())
                .collect(Collectors.toList());
    }

    public Message sendMessage(Long userId, Long programId, ChatRequest chatRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new NotFoundException(PROGRAM_NOT_FOUND));
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Chat chat = Chat.builder()
                .user(user)
                .program(program)
                .content(chatRequest.getContent())
                .dateTime(dateTime)
                .build();
        chatRepository.save(chat);

        return new Message("메시지 전송 성공");
    }
}
