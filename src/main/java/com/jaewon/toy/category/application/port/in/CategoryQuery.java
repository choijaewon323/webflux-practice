package com.jaewon.toy.category.application.port.in;

import com.jaewon.toy.category.application.port.out.dto.CategoryBoardCount;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CategoryQuery {
    Mono<List<CategoryBoardCount>> getCategoriesAndBoardCount();
}
