package com.jaewon.toy.user.adapter.out.persistence;

import reactor.core.publisher.Mono;

public interface UserRepositoryCustom {
    Mono<Long> countAllUsers();
    Mono<Void> updateNickname(String before, String after);
}
