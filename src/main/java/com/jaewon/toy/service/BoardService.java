package com.jaewon.toy.service;

import com.jaewon.toy.domain.Board;
import com.jaewon.toy.domain.LikeType;
import com.jaewon.toy.domain.dto.BoardDetailResponseDto;
import com.jaewon.toy.domain.dto.BoardListResponseDto;
import com.jaewon.toy.domain.dto.BoardSaveRequestDto;
import com.jaewon.toy.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final LikeService likeService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final TransactionalOperator operator;

    public Mono<Boolean> save(BoardSaveRequestDto request) {
        return Mono.zip(categoryService.findByName(request.getCategory()), userService.findByNickname(request.getWriter()))
                .flatMap(tuple -> {
                    long categoryId = tuple.getT1();
                    long userId = tuple.getT2();

                    return boardRepository.save(Board.builder()
                            .title(request.getTitle())
                            .content(request.getContent())
                            .likeCount(0)
                            .userId(userId)
                            .categoryId(categoryId)
                            .build());
                })
                .map(Objects::nonNull)
                .as(operator::transactional);
    }

    public Mono<Boolean> deleteOne(long boardId) {
        return Mono.zip(boardRepository.deleteById(boardId), likeService.deleteAllByType(LikeType.BOARD, boardId))
                .doOnError(throwable -> log.error("failed to delete board id : {}, reason : ", boardId, throwable))
                .map(res -> true);
    }

    public Mono<Boolean> deleteAllByUserId(long userId) {
    }

    public Mono<BoardDetailResponseDto> getOne(long boardId) {
        return boardRepository.findById(boardId)
                .map(BoardDetailResponseDto::toDto);
    }

    public Mono<BoardListResponseDto> getAll() {
        return boardRepository.findAllByRequiredColumn()
                .collectList()
                .map(list -> BoardListResponseDto.builder()
                        .count(list.size())
                        .boardList(list)
                        .build());
    }
}
