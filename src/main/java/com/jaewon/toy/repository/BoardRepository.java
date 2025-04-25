package com.jaewon.toy.repository;

import com.jaewon.toy.domain.Board;
import com.jaewon.toy.domain.dto.BoardRequiredResponseDto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BoardRepository extends ReactiveCrudRepository<Board, Long> {
    Flux<BoardRequiredResponseDto> findAllByRequiredColumn();
    Mono<Void> deleteAllByUserId(long userId);
}
