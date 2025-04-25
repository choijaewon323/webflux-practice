package com.jaewon.toy.repository;

import com.jaewon.toy.domain.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {
    Mono<Long> findByName(String name);
}
