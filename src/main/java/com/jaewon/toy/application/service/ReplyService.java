package com.jaewon.toy.application.service;

import com.jaewon.toy.domain.reply.Reply;
import com.jaewon.toy.domain.reply.dto.ReplyListResponseDto;
import com.jaewon.toy.domain.reply.dto.ReplySaveRequestDto;
import com.jaewon.toy.adapter.out.persistence.reply.ReplyRepository;
import com.jaewon.toy.user.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final UserService userService;
    private final TransactionalOperator operator;

    public Mono<Boolean> save(ReplySaveRequestDto request) {
        return userService.findByNickname(request.getNickname())
                .flatMap(userId -> replyRepository.save(Reply.builder()
                        .boardId(request.getBoardId())
                        .userId(userId)
                        .content(request.getContent())
                        .build()))
                .thenReturn(true)
                .as(operator::transactional);
    }

    public Mono<Boolean> deleteById(long replyId) {
        return replyRepository.deleteById(replyId)
                .thenReturn(true);
    }

    public Mono<Boolean> deleteByUserId(long userId) {
        return replyRepository.deleteByUserId(userId)
                .thenReturn(true);
    }

    public Mono<Boolean> deleteByBoardId(long boardId) {
        return replyRepository.deleteByBoardId(boardId)
                .thenReturn(true);
    }

    public Mono<List<ReplyListResponseDto>> getAllRepliesByBoardId(long boardId) {
        return replyRepository.getAllRepliesByBoardId(boardId)
                .collectList();
    }
}
