package com.jaewon.toy.board.adapter.in.web;

import com.jaewon.toy.board.application.port.in.BoardQuery;
import com.jaewon.toy.board.application.port.in.dto.BoardMinimumFieldResponse;
import com.jaewon.toy.application.service.LogService;
import com.jaewon.toy.board.adapter.in.web.dto.BoardDetailResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/search")
public class BoardSearchController {
    private final LogService logService;
    private final BoardQuery boardQuery;

    @GetMapping
    public Mono<BoardMinimumFieldResponse> searchByKeyword(@RequestParam(required = false) String keyword) {
        Mono<BoardMinimumFieldResponse> results;

        if (keyword == null) {
            results = boardQuery.getBoards();
        } else {
            results = boardQuery.getBoardsKeywordContains(keyword);
        }

        return results
                .doOnError(logService::saveError);
    }

    @GetMapping("/{boardId}")
    public Mono<BoardDetailResponseDto> getOne(@PathVariable Long boardId) {
        if (boardId == null) {
            return Mono.error(new IllegalArgumentException("boardId is empty"));
        }

        return boardQuery.getBoard(boardId)
                .map(board -> new BoardDetailResponseDto(
                        board.getId(),
                        board.getTitle(),
                        board.getContent(),
                        "testWriter",
                        "category",
                        0L,
                        board.getCnt(),
                        board.getCreatedDate()
                ));
    }

    @GetMapping
    public Mono<BoardMinimumFieldResponse> getList() {
        return boardQuery.getBoards();
    }


    @GetMapping("/category/{categoryId}")
    public Mono<BoardMinimumFieldResponse> getAllByCategory(@PathVariable Long categoryId) {
        return boardQuery.getBoardsByCategoryId(categoryId);
    }
}
