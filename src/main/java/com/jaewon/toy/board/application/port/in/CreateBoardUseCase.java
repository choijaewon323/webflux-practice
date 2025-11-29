package com.jaewon.toy.board.application.port.in;

import com.jaewon.toy.board.application.port.in.dto.CreateBoardCommand;
import reactor.core.publisher.Mono;

public interface CreateBoardUseCase {
    Mono<Boolean> createBoard(CreateBoardCommand command);
}
