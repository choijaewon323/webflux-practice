package com.jaewon.toy.category.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends ReactiveCrudRepository<CategoryJpaEntity, Long>, CategoryRepositoryCustom {
    Mono<Long> findIdByName(String name);
}
