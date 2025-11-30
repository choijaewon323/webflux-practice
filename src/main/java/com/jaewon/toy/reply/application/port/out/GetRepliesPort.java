package com.jaewon.toy.reply.application.port.out;

import com.jaewon.toy.reply.domain.Reply;
import reactor.core.publisher.Flux;

public interface GetRepliesPort {
    Flux<Reply> getRepliesByBoardId(long boardId);
}
