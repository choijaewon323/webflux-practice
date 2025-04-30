package com.jaewon.toy.repository;

import com.jaewon.toy.domain.dto.BoardRequiredResponseDto;
import reactor.core.publisher.Flux;

public interface BoardRepositoryCustom {
    Flux<BoardRequiredResponseDto> findAllByRequiredColumn();
}
