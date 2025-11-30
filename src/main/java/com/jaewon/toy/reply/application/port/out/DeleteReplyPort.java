package com.jaewon.toy.reply.application.port.out;

import reactor.core.publisher.Mono;

public interface DeleteReplyPort {
    Mono<Void> deleteById(long replyId);
    Mono<Void> deleteByUserId(long userId);
}
