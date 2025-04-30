package com.jaewon.toy.controller;

import com.jaewon.toy.domain.dto.CategoryListResponseDto;
import com.jaewon.toy.domain.dto.CategorySaveRequestDto;
import com.jaewon.toy.service.CategoryService;
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
    private final CategoryService categoryService;

    @PostMapping
    public Mono<Boolean> save(@RequestBody CategorySaveRequestDto request) {
        return categoryService.save(request)
                .doOnError(throwable -> log.error("failed to save category name : {}. reason : ", request.getName(), throwable))
                .onErrorReturn(false);
    }

    @GetMapping("/all")
    public Mono<List<CategoryListResponseDto>> getAllList() {
        return categoryService.getAllList()
                .doOnError(throwable -> log.error(String.valueOf(throwable)));
    }
}
