package com.jaewon.toy.repository;

import com.jaewon.toy.domain.board.Board;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BoardRepository extends ReactiveCrudRepository<Board, Long>, BoardRepositoryCustom {
    Mono<Void> deleteByUserId(long userId);
}
