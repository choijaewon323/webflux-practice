package com.jaewon.toy.category.application.port.out;

import com.jaewon.toy.category.domain.Category;
import reactor.core.publisher.Mono;

public interface CreateCategoryPort {
    Mono<Void> create(Category newCategory);
}
