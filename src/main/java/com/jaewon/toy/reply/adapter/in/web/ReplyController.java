package com.jaewon.toy.reply.adapter.in.web;

import com.jaewon.toy.reply.adapter.in.web.dto.ReplyListResponseDto;
import com.jaewon.toy.reply.adapter.in.web.dto.ReplySaveRequestDto;
import com.jaewon.toy.reply.application.port.in.CreateReplyUseCase;
import com.jaewon.toy.reply.application.port.in.DeleteReplyUseCase;
import com.jaewon.toy.reply.application.port.in.GetRepliesQuery;
import com.jaewon.toy.util.ValidationUtil;
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
    private final CreateReplyUseCase createReplyUseCase;
    private final DeleteReplyUseCase deleteReplyUseCase;
    private final GetRepliesQuery getRepliesQuery;

    @PostMapping
    public Mono<Boolean> save(@RequestBody ReplySaveRequestDto request) {
        request.validate();

        return createReplyUseCase.create(request.getContent(), request.getUserId(), request.getBoardId())
                .onErrorReturn(false);
    }

    @DeleteMapping("/{replyId}")
    public Mono<Boolean> deleteById(@PathVariable Long replyId) {
        ValidationUtil.checkNull("replyId", replyId);

        return deleteReplyUseCase.deleteById(replyId)
                .onErrorReturn(false);
    }

    @GetMapping("/{boardId}")
    public Mono<List<ReplyListResponseDto>> getAllByBoardId(@PathVariable Long boardId) {
        return getRepliesQuery.getRepliesByBoardId(boardId)
                .map(list -> list.stream()
                        .map(reply -> new ReplyListResponseDto(
                                reply.getId(),
                                "writer",
                                reply.getContent(),
                                reply.getCreatedAt()
                        ))
                        .toList());
    }
}
