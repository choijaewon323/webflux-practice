package com.jaewon.toy.adapter.in.web;

import com.jaewon.toy.domain.reply.dto.ReplyListResponseDto;
import com.jaewon.toy.domain.reply.dto.ReplySaveRequestDto;
import com.jaewon.toy.application.service.LogService;
import com.jaewon.toy.application.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reply")
public class ReplyController {
    private final ReplyService replyService;
    private final LogService logService;

    @PostMapping
    public Mono<Boolean> save(@RequestBody ReplySaveRequestDto request) {
        return replyService.save(request)
                .doOnError(logService::saveError)
                .onErrorReturn(false);
    }

    @DeleteMapping("/{replyId}")
    public Mono<Boolean> deleteById(@PathVariable Long replyId) {
        return replyService.deleteById(replyId)
                .doOnError(logService::saveError)
                .onErrorReturn(false);
    }

    @GetMapping("/{boardId}")
    public Mono<List<ReplyListResponseDto>> getAllByBoardId(@PathVariable Long boardId) {
        return replyService.getAllRepliesByBoardId(boardId)
                .doOnError(logService::saveError);
    }
}
