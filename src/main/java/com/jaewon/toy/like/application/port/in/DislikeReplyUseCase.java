package com.jaewon.toy.like.application.port.in;

import reactor.core.publisher.Mono;

public interface DislikeReplyUseCase {
    Mono<Boolean> dislikeReply(long userId, long replyId);
}
