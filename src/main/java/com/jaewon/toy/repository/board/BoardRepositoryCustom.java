package com.jaewon.toy.repository.board;

import com.jaewon.toy.domain.board.dto.BoardRequiredResponseDto;
import reactor.core.publisher.Flux;

public interface BoardRepositoryCustom {
    Flux<BoardRequiredResponseDto> findAllByRequiredColumn();
    Flux<BoardRequiredResponseDto> findAllByCategoryIdRequiredColumn(long categoryId);
}
