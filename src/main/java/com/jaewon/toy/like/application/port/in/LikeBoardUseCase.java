package com.jaewon.toy.like.application.port.in;

import reactor.core.publisher.Mono;

public interface LikeBoardUseCase {
    Mono<Boolean> likeBoard(long userId, long boardId);
}
