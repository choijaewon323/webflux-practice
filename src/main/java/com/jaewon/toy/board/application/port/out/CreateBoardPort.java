package com.jaewon.toy.board.application.port.out;

import com.jaewon.toy.board.domain.Board;
import reactor.core.publisher.Mono;

public interface CreateBoardPort {
    Mono<Void> create(Board board);
}
