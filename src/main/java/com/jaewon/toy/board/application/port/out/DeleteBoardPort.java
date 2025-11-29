package com.jaewon.toy.board.application.port.out;

import reactor.core.publisher.Mono;

public interface DeleteBoardPort {
    Mono<Void> delete(Long boardId);
}
