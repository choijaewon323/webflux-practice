package com.jaewon.toy.board.application.service;

import com.jaewon.toy.board.application.port.in.BoardQuery;
import com.jaewon.toy.board.application.port.in.dto.BoardMinimumFieldResponse;
import com.jaewon.toy.board.application.port.out.GetBoardDetailPort;
import com.jaewon.toy.board.application.port.out.GetBoardsByCategoryIdPort;
import com.jaewon.toy.board.application.port.out.GetBoardsKeywordContainsPort;
import com.jaewon.toy.board.application.port.out.GetBoardsMinimumFieldPort;
import com.jaewon.toy.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class BoardSearchService implements BoardQuery {
    private final GetBoardsMinimumFieldPort getBoardsMinimumFieldPort;
    private final GetBoardsKeywordContainsPort getBoardsKeywordContainsPort;
    private final GetBoardDetailPort getBoardDetailPort;
    private final GetBoardsByCategoryIdPort getBoardsByCategoryIdPort;

    @Override
    public Mono<BoardMinimumFieldResponse> getBoardsKeywordContains(String keyword) {
        return getBoardsKeywordContainsPort.getBoards(keyword)
                .map(board -> new BoardMinimumFieldResponse.BoardMinimumField(
                        board.getId(),
                        "",
                        board.getTitle(),
                        "",
                        board.getContentToDescription(),
                        board.getCnt(),
                        board.getCreatedDate()
                ))
                .collectList()
                .map(list -> new BoardMinimumFieldResponse(list));
    }

    @Override
    public Mono<BoardMinimumFieldResponse> getBoards() {
        return getBoardsMinimumFieldPort.getBoards()
                .map(board -> new BoardMinimumFieldResponse.BoardMinimumField(
                        board.getId(),
                        "",
                        board.getTitle(),
                        "",
                        board.getContentToDescription(),
                        board.getCnt(),
                        board.getCreatedDate()
                ))
                .collectList()
                .map(list -> new BoardMinimumFieldResponse(list));
    }

    @Override
    public Mono<Board> getBoard(long boardId) {
        return getBoardDetailPort.getBoard(boardId);
    }

    @Override
    public Mono<BoardMinimumFieldResponse> getBoardsByCategoryId(Long categoryId) {
        return getBoardsByCategoryIdPort.getBoardsByCategoryId(categoryId)
                .map(board -> new BoardMinimumFieldResponse.BoardMinimumField(
                        board.getId(),
                        "",
                        board.getTitle(),
                        "",
                        board.getContentToDescription(),
                        board.getCnt(),
                        board.getCreatedDate()
                ))
                .collectList()
                .map(list -> new BoardMinimumFieldResponse(list));
    }
}
