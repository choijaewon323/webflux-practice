package com.jaewon.toy.reply.application.port.out;

import com.jaewon.toy.reply.domain.Reply;
import reactor.core.publisher.Mono;

public interface SaveReplyPort {
    Mono<Void> save(Reply newReply);
}
