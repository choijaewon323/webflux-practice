package com.jaewon.toy.board.adapter.out.persistence;

import com.jaewon.toy.board.domain.dto.BoardRequiredResponseDto;
import reactor.core.publisher.Flux;

public interface BoardRepositoryCustom {
    Flux<BoardRequiredResponseDto> findAllByRequiredColumn();
    Flux<BoardRequiredResponseDto> findAllByCategoryIdRequiredColumn(long categoryId);
    Flux<BoardRequiredResponseDto> findAllByKeywordContains(String keyword);
}
