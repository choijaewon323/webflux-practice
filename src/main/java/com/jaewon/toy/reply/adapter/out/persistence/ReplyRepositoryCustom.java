package com.jaewon.toy.reply.adapter.out.persistence;

import com.jaewon.toy.reply.adapter.in.web.dto.ReplyListResponseDto;
import reactor.core.publisher.Flux;

public interface ReplyRepositoryCustom {
    Flux<ReplyListResponseDto> getAllRepliesByBoardId(long boardId);
}
