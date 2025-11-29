package com.jaewon.toy.board.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BoardRepository extends ReactiveCrudRepository<BoardJpaEntity, Long>, BoardRepositoryCustom {
    Mono<Void> deleteByUserId(long userId);
}
