package com.jaewon.toy.like.adapter.in.web;

import com.jaewon.toy.like.application.port.in.DislikeBoardUseCase;
import com.jaewon.toy.like.application.port.in.DislikeReplyUseCase;
import com.jaewon.toy.like.application.port.in.LikeBoardUseCase;
import com.jaewon.toy.like.application.port.in.LikeReplyUseCase;
import com.jaewon.toy.like.domain.dto.LikeSaveRequestDto;
import com.jaewon.toy.like.application.service.LikeService;
import com.jaewon.toy.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    private final LikeBoardUseCase likeBoardUseCase;
    private final DislikeBoardUseCase dislikeBoardUseCase;
    private final LikeReplyUseCase likeReplyUseCase;
    private final DislikeReplyUseCase dislikeReplyUseCase;

    @PostMapping("/board")
    public Mono<Boolean> likeBoard(@RequestBody LikeSaveRequestDto request) {
        request.validate();

        return likeBoardUseCase.likeBoard(request.getTargetId(), request.getUserId());
    }

    @PostMapping("/reply")
    public Mono<Boolean> likeReply(@RequestBody LikeSaveRequestDto request) {
        request.validate();

        return likeReplyUseCase.likeReply(request.getTargetId(), request.getUserId());
    }

    @DeleteMapping("/board/{boardId}/{userId}")
    public Mono<Boolean> dislikeBoard(@PathVariable Long boardId, @PathVariable Long userId) {
        ValidationUtil.checkNull("boardId", boardId);
        ValidationUtil.checkNull("userId", userId);
        ValidationUtil.checkNumberNegative("boardId", boardId);
        ValidationUtil.checkNumberNegative("userId", userId);

        return dislikeBoardUseCase.dislikeBoard(boardId, userId);
    }

    @DeleteMapping("/reply/{replyId}/{userId}")
    public Mono<Boolean> dislikeReply(@PathVariable Long replyId, @PathVariable Long userId) {
        ValidationUtil.checkNull("replyId", replyId);
        ValidationUtil.checkNull("userId", userId);
        ValidationUtil.checkNumberNegative("replyId", replyId);
        ValidationUtil.checkNumberNegative("userId", userId);


        return dislikeReplyUseCase.dislikeReply(replyId, userId);
    }
}
