package com.jaewon.toy.reply.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ReplyRepository extends ReactiveCrudRepository<ReplyJpaEntity, Long>, ReplyRepositoryCustom {
    Mono<Void> deleteByUserId(long userId);
    Mono<Void> deleteByBoardId(long boardId);
}
