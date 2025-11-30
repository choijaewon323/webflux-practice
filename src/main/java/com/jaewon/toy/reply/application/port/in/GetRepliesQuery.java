package com.jaewon.toy.reply.application.port.in;

import com.jaewon.toy.reply.domain.Reply;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GetRepliesQuery {
    Mono<List<Reply>> getRepliesByBoardId(long boardId);
}
