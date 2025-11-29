package com.jaewon.toy.board.application.port.in;

import com.jaewon.toy.board.application.port.in.dto.BoardMinimumFieldResponse;
import com.jaewon.toy.board.domain.Board;
import reactor.core.publisher.Mono;

public interface BoardQuery {
    Mono<BoardMinimumFieldResponse> getBoards();
    Mono<BoardMinimumFieldResponse> getBoardsKeywordContains(String keyword);
    Mono<Board> getBoard(long boardId);
    Mono<BoardMinimumFieldResponse> getBoardsByCategoryId(Long categoryId);
}
