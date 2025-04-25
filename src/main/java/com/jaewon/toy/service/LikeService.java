package com.jaewon.toy.service;

import com.jaewon.toy.domain.LikeType;
import com.jaewon.toy.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;

    public Mono<Long> deleteAllByType(LikeType likeType, long targetId) {
        return likeRepository.countByTypeAndTargetId(likeType, targetId)
                .flatMap(count -> likeRepository.deleteByTypeAndTargetId(likeType, targetId)
                        .thenReturn(count));
    }
}
