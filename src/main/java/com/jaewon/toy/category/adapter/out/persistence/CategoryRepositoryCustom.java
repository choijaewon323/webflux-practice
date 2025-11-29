package com.jaewon.toy.category.adapter.out.persistence;

import com.jaewon.toy.category.adapter.in.web.dto.CategoryListResponseDto;
import reactor.core.publisher.Flux;

public interface CategoryRepositoryCustom {
    Flux<CategoryListResponseDto> getAll();
}
