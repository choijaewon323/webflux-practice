package com.jaewon.toy.like.application.port.in;

import reactor.core.publisher.Mono;

public interface LikeReplyUseCase {
    Mono<Boolean> likeReply(long userId, long replyId);
}
