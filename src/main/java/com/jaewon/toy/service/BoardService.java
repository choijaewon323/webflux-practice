package com.jaewon.toy.service;

import com.jaewon.toy.domain.board.Board;
import com.jaewon.toy.domain.board.dto.BoardDetailResponseDto;
import com.jaewon.toy.domain.board.dto.BoardListResponseDto;
import com.jaewon.toy.domain.board.dto.BoardSaveRequestDto;
import com.jaewon.toy.domain.like.LikeType;
import com.jaewon.toy.repository.board.BoardRepository;
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
    private final ReplyService replyService;
    private final TransactionalOperator operator;

    public Mono<Boolean> save(BoardSaveRequestDto request) {
        return Mono.zip(categoryService.findByName(request.getCategory()), userService.findByNickname(request.getWriter()))
                .flatMap(tuple -> {
                    long categoryId = tuple.getT1();
                    long userId = tuple.getT2();

                    return boardRepository.save(Board.builder()
                            .title(request.getTitle())
                            .content(request.getContent())
                            .userId(userId)
                            .categoryId(categoryId)
                            .build());
                })
                .map(Objects::nonNull)
                .as(operator::transactional);
    }

    public Mono<Boolean> deleteOne(long boardId) {
        return Mono.zip(boardRepository.deleteById(boardId),
                        likeService.deleteAllByType(LikeType.BOARD, boardId),
                        replyService.deleteByBoardId(boardId))
                .map(res -> true)
                .as(operator::transactional);
    }

    public Mono<BoardDetailResponseDto> getOne(long boardId) {
        return Mono.zip(boardRepository.findById(boardId),
                        likeService.countByBoardId(boardId))
                        .flatMap(tuple -> {
                            Board board = tuple.getT1();
                            long count = tuple.getT2();
                            return Mono.zip(categoryService.findNameById(board.getCategoryId()),
                                    userService.findNicknameById(board.getUserId()))
                                    .flatMap(tup -> {
                                        String category = tup.getT1();
                                        String nickname = tup.getT2();
                                        BoardDetailResponseDto result = BoardDetailResponseDto.builder()
                                                .id(board.getId())
                                                .likeCount(count)
                                                .writer(nickname)
                                                .content(board.getContent())
                                                .updatedAt(board.getUpdatedAt())
                                                .createdAt(board.getCreatedAt())
                                                .title(board.getTitle())
                                                .category(category)
                                                .cnt(board.getCnt())
                                                .build();
                                        board.increaseCnt();
                                        return boardRepository.save(board)
                                                .thenReturn(result);
                                    });
                        })
                .as(operator::transactional);
    }

    public Mono<BoardListResponseDto> getAll() {
        return boardRepository.findAllByRequiredColumn()
                .collectList()
                .map(list -> BoardListResponseDto.builder()
                        .count(list.size())
                        .boardList(list)
                        .build());
    }

    public Mono<Boolean> deleteByUserId(long userId) {
        return boardRepository.deleteByUserId(userId)
                .thenReturn(true);
    }

    public Mono<BoardListResponseDto> getAllByCategoryId(long categoryId) {
        return boardRepository.findAllByCategoryIdRequiredColumn(categoryId)
                .collectList()
                .map(list -> BoardListResponseDto.builder()
                        .count(list.size())
                        .boardList(list)
                        .build());
    }
}
