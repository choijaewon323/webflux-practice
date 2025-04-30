package com.jaewon.toy.controller;

import com.jaewon.toy.domain.dto.CategorySaveRequestDto;
import com.jaewon.toy.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
}
