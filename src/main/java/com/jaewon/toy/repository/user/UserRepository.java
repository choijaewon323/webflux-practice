package com.jaewon.toy.repository.user;

import com.jaewon.toy.domain.user.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long>, UserRepositoryCustom {
    Mono<Long> findIdByNickname(String nickname);
    Mono<Long> findIdByEmail(String email);
    Mono<User> findByEmail(String email);
}
