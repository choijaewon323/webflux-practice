package com.jaewon.toy.repository.reply;

import com.jaewon.toy.domain.reply.Reply;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ReplyRepository extends ReactiveCrudRepository<Reply, Long>, ReplyRepositoryCustom {
    Mono<Void> deleteByUserId(long userId);
    Mono<Void> deleteByBoardId(long boardId);
}
