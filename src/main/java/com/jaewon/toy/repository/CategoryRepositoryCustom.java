package com.jaewon.toy.repository;

import com.jaewon.toy.domain.category.dto.CategoryListResponseDto;
import reactor.core.publisher.Flux;

public interface CategoryRepositoryCustom {
    Flux<CategoryListResponseDto> getAll();
}
