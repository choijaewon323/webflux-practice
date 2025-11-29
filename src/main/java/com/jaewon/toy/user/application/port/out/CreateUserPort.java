package com.jaewon.toy.user.application.port.out;

import com.jaewon.toy.user.domain.User;
import reactor.core.publisher.Mono;

public interface CreateUserPort {
    Mono<Void> save(User user);
}
