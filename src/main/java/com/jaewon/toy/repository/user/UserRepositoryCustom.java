package com.jaewon.toy.repository.user;

import com.jaewon.toy.domain.user.dto.UserRequiredResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepositoryCustom {
    Flux<UserRequiredResponseDto> getAllUsersRequiredColumn();
    Mono<Long> countAllUsers();
    Mono<Void> updateNickname(String before, String after);
}
