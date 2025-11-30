package com.jaewon.toy.reply.application.port.in;

import reactor.core.publisher.Mono;

public interface DeleteReplyUseCase {
    Mono<Boolean> deleteById(long replyId);
}
