package com.jaewon.toy.user.application.port.in;

import reactor.core.publisher.Mono;

public interface CreateUserUseCase {
    Mono<Boolean> create(String email, String password, String nickname);
}
