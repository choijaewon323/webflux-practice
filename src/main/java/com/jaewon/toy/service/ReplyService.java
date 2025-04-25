package com.jaewon.toy.service;

import com.jaewon.toy.domain.Reply;
import com.jaewon.toy.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public Mono<Boolean> save() {
        return Mono.empty();
    }

    public Flux<Reply> getRepliesByBoardId(long boardId) {
        return replyRepository.findAllByBoardId(boardId);
    }
}
