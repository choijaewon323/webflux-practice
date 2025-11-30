package com.jaewon.toy.reply.application.service;

import com.jaewon.toy.reply.application.port.in.CreateReplyUseCase;
import com.jaewon.toy.reply.application.port.in.DeleteReplyUseCase;
import com.jaewon.toy.reply.application.port.in.GetRepliesQuery;
import com.jaewon.toy.reply.application.port.out.DeleteReplyPort;
import com.jaewon.toy.reply.application.port.out.GetRepliesPort;
import com.jaewon.toy.reply.application.port.out.SaveReplyPort;
import com.jaewon.toy.reply.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService implements CreateReplyUseCase, DeleteReplyUseCase, GetRepliesQuery {
    private final SaveReplyPort saveReplyPort;
    private final DeleteReplyPort deleteReplyPort;
    private final GetRepliesPort getRepliesPort;

    @Override
    public Mono<Boolean> create(String content, long userId, long boardId) {
        return Mono.fromCallable(() -> {
            Reply newReply = Reply.newReply(content, userId, boardId);

            return saveReplyPort.save(newReply);
        }).thenReturn(true)
                .onErrorReturn(false);
    }

    @Override
    public Mono<Boolean> deleteById(long replyId) {
        return deleteReplyPort.deleteById(replyId)
                .thenReturn(true)
                .onErrorReturn(false);
    }

    @Override
    public Mono<List<Reply>> getRepliesByBoardId(long boardId) {
        return getRepliesPort.getRepliesByBoardId(boardId)
                .collectList();
    }
}
