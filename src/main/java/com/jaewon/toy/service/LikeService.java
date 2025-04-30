package com.jaewon.toy.service;

import com.jaewon.toy.domain.Like;
import com.jaewon.toy.domain.LikeType;
import com.jaewon.toy.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final TransactionalOperator operator;

    public Mono<Long> deleteAllByType(LikeType likeType, long targetId) {
        return likeRepository.countByTypeAndTargetId(likeType, targetId)
                .flatMap(count -> likeRepository.deleteByTypeAndTargetId(likeType, targetId)
                        .thenReturn(count));
    }

    public Mono<Long> countByBoardId(long boardId) {
        return likeRepository.countByTypeAndTargetId(LikeType.BOARD, boardId);
    }

    public Mono<Boolean> likeBoard(long boardId, String nickname) {
        return userService.findByNickname(nickname)
                .flatMap(userId -> likeRepository.isLiked(userId, boardId, LikeType.BOARD)
                        .flatMap(isLiked -> {
                            if (isLiked) {
                                return Mono.error(new RuntimeException("이미 좋아요를 한 게시물입니다"));
                            }
                            return likeRepository.save(Like.builder()
                                    .userId(userId)
                                    .targetId(boardId)
                                    .likeType(LikeType.BOARD)
                                    .build());
                        }))
                .thenReturn(true)
                .as(operator::transactional);
    }

    public Mono<Boolean> likeReply(long replyId, String nickname) {
        return userService.findByNickname(nickname)
                .flatMap(userId -> likeRepository.isLiked(userId, replyId, LikeType.REPLY)
                        .flatMap(isLiked -> {
                            if (isLiked) {
                                return Mono.error(new RuntimeException("이미 좋아요를 한 댓글입니다"));
                            }
                            return likeRepository.save(Like.builder()
                                    .userId(userId)
                                    .targetId(replyId)
                                    .likeType(LikeType.REPLY)
                                    .build());
                        }))
                .thenReturn(true)
                .as(operator::transactional);
    }

    public Mono<Boolean> dislikeBoard(long boardId, String nickname) {
        return userService.findByNickname(nickname)
                .flatMap(userId -> likeRepository.isLiked(userId, boardId, LikeType.BOARD)
                        .flatMap(isLiked -> {
                            if (!isLiked) {
                                return Mono.error(new RuntimeException("이미 좋아요를 취소한 게시물입니다"));
                            }
                            return likeRepository.deleteByTypeAndTargetIdAndUserId(LikeType.BOARD, boardId, userId);
                        }))
                .as(operator::transactional);
    }

    public Mono<Boolean> dislikeReply(long replyId, String nickname) {
        return userService.findByNickname(nickname)
                .flatMap(userId -> likeRepository.isLiked(userId, replyId, LikeType.REPLY)
                        .flatMap(isLiked -> {
                            if (!isLiked) {
                                return Mono.error(new RuntimeException("이미 좋아요를 취소한 댓글입니다"));
                            }
                            return likeRepository.deleteByTypeAndTargetIdAndUserId(LikeType.REPLY, replyId, userId);
                        }))
                .as(operator::transactional);
    }

    public Mono<Boolean> deleteByUserId(long userId) {
        return likeRepository.deleteByUserId(userId)
                .thenReturn(true);
    }
}
