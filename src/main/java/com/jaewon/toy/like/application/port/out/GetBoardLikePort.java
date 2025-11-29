package com.jaewon.toy.like.application.port.out;

import com.jaewon.toy.like.domain.Like;
import reactor.core.publisher.Mono;

public interface GetBoardLikePort {
    Mono<Like> getBoardLike(long userId, long targetId);
}
