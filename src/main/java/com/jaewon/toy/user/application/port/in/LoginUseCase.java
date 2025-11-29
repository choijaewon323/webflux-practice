package com.jaewon.toy.user.application.port.in;

import reactor.core.publisher.Mono;

public interface LoginUseCase {
    Mono<Boolean> login(String email, String password);
}
