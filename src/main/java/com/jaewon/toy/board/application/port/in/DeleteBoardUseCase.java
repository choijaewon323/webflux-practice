package com.jaewon.toy.board.application.port.in;

import reactor.core.publisher.Mono;

public interface DeleteBoardUseCase {
    Mono<Boolean> delete(Long boardId);
}
