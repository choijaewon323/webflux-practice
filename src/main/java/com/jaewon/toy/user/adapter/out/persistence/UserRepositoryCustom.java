package com.jaewon.toy.user.adapter.out.persistence;

import com.jaewon.toy.user.domain.dto.UserRequiredResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepositoryCustom {
    Flux<UserRequiredResponseDto> getAllUsersRequiredColumn();
    Mono<Long> countAllUsers();
    Mono<Void> updateNickname(String before, String after);
}
