package com.jaewon.toy.repository.category;

import com.jaewon.toy.domain.category.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Long>, CategoryRepositoryCustom {
    Mono<Long> findIdByName(String name);
}
