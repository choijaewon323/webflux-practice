package com.jaewon.toy.like.application.port.in;

import reactor.core.publisher.Mono;

public interface DislikeBoardUseCase {
    Mono<Boolean> dislikeBoard(long userId, long boardId);
}
