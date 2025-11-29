package com.jaewon.toy.category.application.port.in;

import reactor.core.publisher.Mono;

public interface CreateCategoryUseCase {
    Mono<Boolean> create(String categoryName);
}
