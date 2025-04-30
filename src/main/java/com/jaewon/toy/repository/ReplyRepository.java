package com.jaewon.toy.repository;

import com.jaewon.toy.domain.Reply;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ReplyRepository extends ReactiveCrudRepository<Reply, Long> {
    Mono<Void> deleteByUserId(long userId);
}
