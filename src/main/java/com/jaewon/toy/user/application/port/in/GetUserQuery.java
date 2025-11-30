package com.jaewon.toy.user.application.port.in;

import com.jaewon.toy.user.domain.User;
import reactor.core.publisher.Flux;

public interface GetUserQuery {
    Flux<User> getUsers();
}
