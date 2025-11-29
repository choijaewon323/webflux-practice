package com.jaewon.toy.board.adapter.in.web;

import com.jaewon.toy.board.application.port.in.CreateBoardUseCase;
import com.jaewon.toy.board.application.port.in.DeleteBoardUseCase;
import com.jaewon.toy.board.application.port.in.dto.CreateBoardCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
@RestController
public class BoardController {
    private final CreateBoardUseCase createBoardUseCase;
    private final DeleteBoardUseCase deleteBoardUseCase;

    @PostMapping
    public Mono<Boolean> save(@RequestBody CreateBoardCommand command) {
        return createBoardUseCase.createBoard(command);
    }

    @DeleteMapping("/{boardId}")
    public Mono<Boolean> deleteOne(@PathVariable Long boardId) {
        return deleteBoardUseCase.delete(boardId);
    }
}
