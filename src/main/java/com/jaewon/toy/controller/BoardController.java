package com.jaewon.toy.controller;

import com.jaewon.toy.domain.dto.BoardDetailResponseDto;
import com.jaewon.toy.domain.dto.BoardListResponseDto;
import com.jaewon.toy.domain.dto.BoardSaveRequestDto;
import com.jaewon.toy.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/{boardId}")
    public Mono<BoardDetailResponseDto> getOne(@PathVariable Long boardId) {
        return boardService.getOne(boardId)
                .doOnError(throwable -> log.error(String.valueOf(throwable)));
    }

    @GetMapping
    public Mono<BoardListResponseDto> getList() {
        return boardService.getAll()
                .doOnError(throwable -> log.error(String.valueOf(throwable)));
    }

    @PostMapping
    public Mono<Boolean> saveOne(@RequestBody BoardSaveRequestDto request) {
        return boardService.save(request)
                .doOnError(throwable -> log.error(String.valueOf(throwable)));
    }

    @DeleteMapping("/{boardId}")
    public Mono<Boolean> deleteOne(@PathVariable Long boardId) {
        return boardService.deleteOne(boardId)
                .doOnError(throwable -> log.error(String.valueOf(throwable)));
    }
}
