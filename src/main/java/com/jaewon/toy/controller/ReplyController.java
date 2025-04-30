package com.jaewon.toy.controller;

import com.jaewon.toy.domain.dto.ReplySaveRequestDto;
import com.jaewon.toy.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reply")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/{boardId}")
    public Mono<Boolean> save(@PathVariable Long boardId, @RequestBody ReplySaveRequestDto request) {
        return replyService.save(boardId, request)
                .doOnError(throwable -> log.error(String.valueOf(throwable)))
                .onErrorReturn(false);
    }

    @DeleteMapping("/{replyId}")
    public Mono<Boolean> deleteById(@PathVariable Long replyId) {
        return replyService.deleteById(replyId)
                .doOnError(throwable -> log.error(String.valueOf(throwable)))
                .onErrorReturn(false);
    }
}
