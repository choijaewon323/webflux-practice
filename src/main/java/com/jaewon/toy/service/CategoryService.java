package com.jaewon.toy.service;

import com.jaewon.toy.domain.category.Category;
import com.jaewon.toy.domain.category.dto.CategoryListResponseDto;
import com.jaewon.toy.domain.category.dto.CategorySaveRequestDto;
import com.jaewon.toy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Mono<Boolean> save(CategorySaveRequestDto request) {
        return categoryRepository.save(Category.builder()
                .name(request.getName())
                .build())
                .thenReturn(true);
    }

    public Mono<Long> findByName(String category) {
        return categoryRepository.findIdByName(category)
                .switchIfEmpty(Mono.error(new RuntimeException("해당 카테고리가 없음")))
                .flatMap(Mono::just);
    }

    public Mono<String> findNameById(long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(Category::getName);
    }

    public Mono<List<CategoryListResponseDto>> getAllList() {
        return categoryRepository.getAll()
                .collectList();
    }
}
