package com.jaewon.toy.board.application.service;

import com.jaewon.toy.board.application.port.in.CreateBoardUseCase;
import com.jaewon.toy.board.application.port.in.DeleteBoardUseCase;
import com.jaewon.toy.board.application.port.out.CreateBoardPort;
import com.jaewon.toy.board.application.port.out.DeleteBoardPort;
import com.jaewon.toy.board.domain.Board;
import com.jaewon.toy.board.application.port.in.dto.CreateBoardCommand;
import com.jaewon.toy.board.adapter.out.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService implements CreateBoardUseCase, DeleteBoardUseCase {
    private final BoardRepository boardRepository;
    private final CreateBoardPort createBoardPort;
    private final DeleteBoardPort deleteBoardPort;

    @Override
    public Mono<Boolean> createBoard(CreateBoardCommand command) {
        return Mono.fromCallable(() -> {
            Board board = Board.newBoard(command);

            return createBoardPort.create(board);
        })
                .thenReturn(true)
                .onErrorReturn(false);
    }

    @Override
    public Mono<Boolean> delete(Long boardId) {
        return deleteBoardPort.delete(boardId)
                .thenReturn(true)
                .onErrorReturn(false);
    }

    public Mono<Boolean> deleteByUserId(long userId) {
        return boardRepository.deleteByUserId(userId)
                .thenReturn(true);
    }
}
