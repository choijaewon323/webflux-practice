package com.jaewon.toy.controller;

import com.jaewon.toy.domain.board.dto.BoardListResponseDto;
import com.jaewon.toy.service.BoardSearchService;
import com.jaewon.toy.service.BoardService;
import com.jaewon.toy.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    private final LogService logService;
    private final BoardSearchService boardSearchService;
    private final BoardService boardService;

    @GetMapping
    public Mono<BoardListResponseDto> searchByKeyword(@RequestParam(required = false) String keyword) {
        Mono<BoardListResponseDto> results;

        if (keyword == null) {
            results = boardService.getAll();
        } else {
            results = boardSearchService.searchByKeyword(keyword);
        }

        return results
                .doOnError(logService::saveError);
    }
}
