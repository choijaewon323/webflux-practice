package com.jaewon.toy.repository;

import com.jaewon.toy.domain.Like;
import com.jaewon.toy.domain.LikeType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface LikeRepository extends ReactiveCrudRepository<Like, Long> {
    Mono<Boolean> deleteByTypeAndTargetId(LikeType likeType, long targetId);
    Mono<Long> countByTypeAndTargetId(LikeType likeType, long targetId);
}
