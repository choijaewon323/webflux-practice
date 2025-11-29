package com.jaewon.toy.adapter.out.persistence.reply;

import com.jaewon.toy.domain.reply.dto.ReplyListResponseDto;
import reactor.core.publisher.Flux;

public interface ReplyRepositoryCustom {
    Flux<ReplyListResponseDto> getAllRepliesByBoardId(long boardId);
}
