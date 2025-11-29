package com.jaewon.toy.like.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface LikeRepository extends ReactiveCrudRepository<LikeJpaEntity, Long>, LikeRepositoryCustom {
    Mono<Void> deleteByUserId(long userId);
}
