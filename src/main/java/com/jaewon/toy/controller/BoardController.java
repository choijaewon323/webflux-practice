package com.jaewon.toy.controller;

import com.jaewon.toy.domain.board.dto.BoardDetailResponseDto;
import com.jaewon.toy.domain.board.dto.BoardListResponseDto;
import com.jaewon.toy.domain.board.dto.BoardSaveRequestDto;
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
        return boardService.getOne(boardId);
    }

    @GetMapping
    public Mono<BoardListResponseDto> getList() {
        return boardService.getAll();
    }

    @PostMapping
    public Mono<Boolean> save(@RequestBody BoardSaveRequestDto request) {
        return boardService.save(request);
    }

    @DeleteMapping("/{boardId}")
    public Mono<Boolean> deleteOne(@PathVariable Long boardId) {
        return boardService.deleteOne(boardId);
    }

    @GetMapping("/category/{categoryId}")
    public Mono<BoardListResponseDto> getALlByCategory(@PathVariable Long categoryId) {
        return boardService.getAllByCategoryId(categoryId);
    }
}
