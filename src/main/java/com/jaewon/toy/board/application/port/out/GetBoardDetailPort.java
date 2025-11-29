package com.jaewon.toy.board.application.port.out;

import com.jaewon.toy.board.domain.Board;
import reactor.core.publisher.Mono;

public interface GetBoardDetailPort {
    Mono<Board> getBoard(Long boardId);
}
