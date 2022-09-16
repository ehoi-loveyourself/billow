package com.billow.model.service.user;

import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.program.Program;
import com.billow.domain.entity.user.Bookmark;
import com.billow.domain.entity.user.User;
import com.billow.exception.BadRequestException;
import com.billow.exception.NotFoundException;
import com.billow.model.repository.program.ProgramRepository;
import com.billow.model.repository.user.BookmarkRepository;
import com.billow.model.repository.user.UserRepository;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookmarkService {

    private static final String PROGRAM_NOT_FOUND = "해당 프로그램을 찾을 수 없습니다.";
    private static final String USER_NOT_FOUND = "해당 유저를 찾을 수 없습니다.";
    private static final String BOOKMARK_NOT_FOUND = "해당 즐겨찾기를 찾을 수 없습니다.";
    private static final String BAD_REQUEST = "잘못된 요청입니다.";

    private final BookmarkRepository bookmarkRepository;
    private final ProgramRepository programRepository;
    private final UserRepository userRepository;

    public List<ProgramResponse> selectBookmark(Long userId) {
        return bookmarkRepository.findByUser_Id(userId)
                .stream()
                .map(bookmark -> ProgramResponse.builder()
                        .title(bookmark.getProgram().getTitle())
                        .genres(bookmark.getProgram().getGenreList()
                                .stream()
                                .map(genre -> genre.getGenreInfo().getName())
                                .collect(Collectors.toList()))
                        .age(bookmark.getProgram().getAge())
                        .summary(bookmark.getProgram().getSummary())
                        .broadcastingDay(bookmark.getProgram().getBroadcastingDay())
                        .broadcastingTime(bookmark.getProgram().getBroadcastingTime())
                        .broadcastingStation(bookmark.getProgram().getBroadcastingStation())
                        .endFlag(bookmark.getProgram().isEndFlag())
                        .averageRating(bookmark.getProgram().getAverageRating())
                        .posterImg(bookmark.getProgram().getPosterImg())
                        .build())
                .collect(Collectors.toList());
    }

    public Message postBookmark(Long userId, Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new NotFoundException(PROGRAM_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .program(program)
                .build();
        bookmarkRepository.save(bookmark);
        return new Message("프로그램을 즐겨찾기에 담았습니다.");
    }

    public Message deleteBookmark(Long userId, Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new NotFoundException(BOOKMARK_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        if (!bookmark.getUser().equals(user)) {
            throw new BadRequestException(BAD_REQUEST);
        }
        bookmarkRepository.delete(bookmark);
        return new Message("프로그램을 즐겨찾기에서 삭제했습니다.");
    }
}
