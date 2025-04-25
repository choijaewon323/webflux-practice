package com.jaewon.toy.service;

import com.jaewon.toy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Mono<Long> findByName(String category) {
        return categoryRepository.findByName(category)
                .flatMap(id -> {
                    if (id == null) {
                        return Mono.error(new RuntimeException("해당 카테고리가 없음"));
                    }
                    return Mono.just(id);
                });
    }
}
