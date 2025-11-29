package com.jaewon.toy.category.application.port.out;

import com.jaewon.toy.category.application.port.out.dto.CategoryBoardCount;
import reactor.core.publisher.Flux;

public interface GetAllCategoriesAndBoardCountPort {
    Flux<CategoryBoardCount> getAll();
}
