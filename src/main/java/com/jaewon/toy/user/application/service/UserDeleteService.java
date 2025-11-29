package com.jaewon.toy.user.application.service;

import com.jaewon.toy.application.service.ReplyService;
import com.jaewon.toy.board.application.service.BoardService;
import com.jaewon.toy.like.application.service.LikeService;
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

    public Mono<Boolean> deleteByEmail(String email) {
        return userService.findByEmail(email)
                .flatMap(userId -> Mono.zip(userService.deleteByUserId(userId),
                        boardService.deleteByUserId(userId),
                        replyService.deleteByUserId(userId),
                        likeService.deleteByUserId(userId)))
                .thenReturn(true)
                .as(operator::transactional);
    }
}
