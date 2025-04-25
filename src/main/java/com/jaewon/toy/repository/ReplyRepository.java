package com.jaewon.toy.repository;

import com.jaewon.toy.domain.Reply;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ReplyRepository extends ReactiveCrudRepository<Reply, Long> {
    Flux<Reply> findAllByBoardId(long boardId);
}
