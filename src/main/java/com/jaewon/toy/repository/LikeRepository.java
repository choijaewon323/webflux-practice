package com.jaewon.toy.repository;

import com.jaewon.toy.domain.Like;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface LikeRepository extends ReactiveCrudRepository<Like, Long>, LikeRepositoryCustom {
    Mono<Void> deleteByUserId(long userId);
}
