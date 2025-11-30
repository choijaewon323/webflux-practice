package com.jaewon.toy.reply.application.port.in;

import reactor.core.publisher.Mono;

public interface CreateReplyUseCase {
    Mono<Boolean> create(String content, long userId, long boardId);
}
