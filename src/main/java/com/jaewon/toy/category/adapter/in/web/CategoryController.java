package com.jaewon.toy.category.adapter.in.web;

import com.jaewon.toy.category.application.port.in.CategoryQuery;
import com.jaewon.toy.category.application.port.in.CreateCategoryUseCase;
import com.jaewon.toy.category.adapter.in.web.dto.CategoryListResponseDto;
import com.jaewon.toy.category.adapter.in.web.dto.CategorySaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final CategoryQuery categoryQuery;

    @PostMapping
    public Mono<Boolean> save(@RequestBody CategorySaveRequestDto request) {
        return createCategoryUseCase.create(request.getName());
    }

    @GetMapping("/all")
    public Mono<List<CategoryListResponseDto>> getAllList() {
        return categoryQuery.getCategoriesAndBoardCount()
                .map(list -> list.stream()
                        .map(categoryBoardCount -> new CategoryListResponseDto(
                                categoryBoardCount.getCount(),
                                categoryBoardCount.getName()
                        ))
                        .toList());
    }
}
