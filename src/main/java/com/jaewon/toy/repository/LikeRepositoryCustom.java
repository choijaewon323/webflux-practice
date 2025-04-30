package com.jaewon.toy.repository;

import com.jaewon.toy.domain.like.LikeType;
import reactor.core.publisher.Mono;

public interface LikeRepositoryCustom {
    Mono<Boolean> deleteByTypeAndTargetId(LikeType likeType, long targetId);
    Mono<Long> countByTypeAndTargetId(LikeType likeType, long targetId);
    Mono<Boolean> isLiked(long userId, long targetId, LikeType likeType);
    Mono<Boolean> deleteByTypeAndTargetIdAndUserId(LikeType likeType, long targetId, long userId);
}
