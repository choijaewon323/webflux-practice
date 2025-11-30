package com.jaewon.toy.user.application.port.in;

import reactor.core.publisher.Mono;

public interface DeleteUserByEmailUseCase {
    Mono<Boolean> delete(String email);
}
