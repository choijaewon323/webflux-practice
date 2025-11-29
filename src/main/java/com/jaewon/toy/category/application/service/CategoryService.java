package com.jaewon.toy.category.application.service;

import com.jaewon.toy.category.application.port.in.CategoryQuery;
import com.jaewon.toy.category.application.port.in.CreateCategoryUseCase;
import com.jaewon.toy.category.application.port.out.CreateCategoryPort;
import com.jaewon.toy.category.application.port.out.GetAllCategoriesAndBoardCountPort;
import com.jaewon.toy.category.application.port.out.dto.CategoryBoardCount;
import com.jaewon.toy.category.domain.Category;
import com.jaewon.toy.category.adapter.out.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService implements CreateCategoryUseCase, CategoryQuery {
    private final CreateCategoryPort createCategoryPort;
    private final GetAllCategoriesAndBoardCountPort getAllCategoriesAndBoardCountPort;

    @Override
    public Mono<Boolean> create(String categoryName) {
        return Mono.just(Category.create(categoryName))
                .flatMap(newCategory -> createCategoryPort.create(newCategory))
                .thenReturn(true)
                .onErrorReturn(false);
    }

    @Override
    public Mono<List<CategoryBoardCount>> getCategoriesAndBoardCount() {
        return getAllCategoriesAndBoardCountPort.getAll()
                .collectList();
    }
}
