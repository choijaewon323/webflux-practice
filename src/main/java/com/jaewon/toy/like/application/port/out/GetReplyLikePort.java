package com.jaewon.toy.like.application.port.out;

import com.jaewon.toy.like.domain.Like;
import reactor.core.publisher.Mono;

public interface GetReplyLikePort {
    Mono<Like> getReplyLike(long userId, long replyId);
}
