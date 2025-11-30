package com.jaewon.toy.user.application.port.out;

import reactor.core.publisher.Mono;

public interface DeleteUserPort {
    Mono<Void> delete(long userId);
}
