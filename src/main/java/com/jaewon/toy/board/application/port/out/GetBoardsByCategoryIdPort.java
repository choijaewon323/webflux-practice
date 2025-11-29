package com.jaewon.toy.board.application.port.out;

import com.jaewon.toy.board.domain.Board;
import reactor.core.publisher.Flux;

public interface GetBoardsByCategoryIdPort {
    Flux<Board> getBoardsByCategoryId(Long categoryId);
}
