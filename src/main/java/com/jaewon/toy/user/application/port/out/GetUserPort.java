package com.jaewon.toy.user.application.port.out;

import com.jaewon.toy.user.domain.User;
import reactor.core.publisher.Mono;

public interface GetUserPort {
    Mono<User> getUserByEmail(String email);
}
