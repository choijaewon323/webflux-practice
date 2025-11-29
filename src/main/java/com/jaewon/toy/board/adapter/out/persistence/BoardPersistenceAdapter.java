package com.jaewon.toy.board.adapter.out.persistence;

import com.jaewon.toy.board.application.port.out.CreateBoardPort;
import com.jaewon.toy.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class BoardPersistenceAdapter implements CreateBoardPort {
    private final BoardRepository boardRepository;

    @Override
    public Mono<Void> create(Board board) {
        return boardRepository.save(BoardJpaEntity.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .userId(0L)
                .categoryId(0L)
                .build())
                .then();
    }
}
