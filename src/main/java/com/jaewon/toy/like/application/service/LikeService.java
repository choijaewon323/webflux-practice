package com.jaewon.toy.like.application.service;

import com.jaewon.toy.like.adapter.out.persistence.LikeRepository;
import com.jaewon.toy.like.application.port.in.DislikeBoardUseCase;
import com.jaewon.toy.like.application.port.in.DislikeReplyUseCase;
import com.jaewon.toy.like.application.port.in.LikeBoardUseCase;
import com.jaewon.toy.like.application.port.in.LikeReplyUseCase;
import com.jaewon.toy.like.application.port.out.GetBoardLikePort;
import com.jaewon.toy.like.application.port.out.GetReplyLikePort;
import com.jaewon.toy.like.application.port.out.SaveLikePort;
import com.jaewon.toy.like.domain.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class LikeService implements LikeBoardUseCase, DislikeBoardUseCase, LikeReplyUseCase, DislikeReplyUseCase {
    private final LikeRepository likeRepository;
    private final GetBoardLikePort getBoardLikePort;
    private final GetReplyLikePort getReplyLikePort;
    private final SaveLikePort saveLikePort;

    @Override
    public Mono<Boolean> likeBoard(long userId, long boardId) {
        return getBoardLikePort.getBoardLike(userId, boardId)
                .switchIfEmpty(Mono.defer(() -> {
                    Like boardLike = Like.createBoardLike(userId, boardId);
                    boardLike.like();

                    return Mono.just(boardLike);
                }))
                .flatMap(like -> saveLikePort.save(like))
                .thenReturn(true)
                .onErrorReturn(false);
    }

    @Override
    public Mono<Boolean> dislikeBoard(long userId, long boardId) {
        return getBoardLikePort.getBoardLike(userId, boardId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("wrong dislike operation")))
                .flatMap(like -> saveLikePort.save(like))
                .thenReturn(true)
                .onErrorReturn(false);
    }

    @Override
    public Mono<Boolean> dislikeReply(long userId, long replyId) {
        return getReplyLikePort.getReplyLike(userId, replyId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("wrong dislike operation")))
                .flatMap(like -> saveLikePort.save(like))
                .thenReturn(true)
                .onErrorReturn(false);
    }

    @Override
    public Mono<Boolean> likeReply(long userId, long replyId) {
        return getReplyLikePort.getReplyLike(userId, replyId)
                .switchIfEmpty(Mono.defer(() -> {
                    Like boardLike = Like.createBoardLike(userId, replyId);
                    boardLike.dislike();

                    return Mono.just(boardLike);
                }))
                .flatMap(like -> saveLikePort.save(like))
                .thenReturn(true)
                .onErrorReturn(false);
    }

    public Mono<Boolean> deleteByUserId(long userId) {
        return likeRepository.deleteByUserId(userId)
                .thenReturn(true);
    }
}
