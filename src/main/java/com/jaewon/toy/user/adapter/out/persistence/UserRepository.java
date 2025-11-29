package com.jaewon.toy.user.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserJpaEntity, Long>, UserRepositoryCustom {
    Mono<Long> findIdByNickname(String nickname);
    Mono<Long> findIdByEmail(String email);
    Mono<UserJpaEntity> findByEmail(String email);
}
