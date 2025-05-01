package com.jaewon.toy.controller;

import com.jaewon.toy.domain.like.dto.LikeSaveRequestDto;
import com.jaewon.toy.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/board")
    public Mono<Boolean> likeBoard(@RequestBody LikeSaveRequestDto request) {
        return likeService.likeBoard(request.getTargetId(), request.getNickname());
    }

    @PostMapping("/reply")
    public Mono<Boolean> likeReply(@RequestBody LikeSaveRequestDto request) {
        return likeService.likeReply(request.getTargetId(), request.getNickname());
    }

    @DeleteMapping("/board/{boardId}/{nickname}")
    public Mono<Boolean> dislikeBoard(@PathVariable Long boardId, @PathVariable String nickname) {
        return likeService.dislikeBoard(boardId, nickname);
    }

    @DeleteMapping("/reply/{replyId}/{nickname}")
    public Mono<Boolean> dislikeReply(@PathVariable Long replyId, @PathVariable String nickname) {
        return likeService.dislikeReply(replyId, nickname);
    }
}
