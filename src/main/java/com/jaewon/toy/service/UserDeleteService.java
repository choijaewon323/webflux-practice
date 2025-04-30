package com.jaewon.toy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserDeleteService {
    private final UserService userService;
    private final BoardService boardService;
    private final ReplyService replyService;
    private final LikeService likeService;
    private final TransactionalOperator operator;

    public Mono<Boolean> deleteByNickname(String nickname) {
        return userService.findByNickname(nickname)
                .flatMap(userId -> Mono.zip(boardService.deleteByUserId(userId),
                        replyService.deleteByUserId(userId),
                        likeService.deleteByUserId(userId)))
                .thenReturn(true)
                .as(operator::transactional);
    }
}
