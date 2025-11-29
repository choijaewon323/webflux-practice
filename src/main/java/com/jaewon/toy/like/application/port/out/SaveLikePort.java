package com.jaewon.toy.like.application.port.out;

import com.jaewon.toy.like.domain.Like;
import reactor.core.publisher.Mono;

public interface SaveLikePort {
    Mono<Void> save(Like like);
}
