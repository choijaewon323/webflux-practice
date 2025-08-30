package com.jaewon.toy.service;

import com.jaewon.toy.domain.board.dto.BoardListResponseDto;
import com.jaewon.toy.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class BoardSearchService {
    private final BoardRepository boardRepository;

    public Mono<BoardListResponseDto> searchByKeyword(String keyword) {
        return boardRepository.findAllByKeywordContains(keyword)
                .collectList()
                .map(list -> BoardListResponseDto.builder()
                        .count(list.size())
                        .boardList(list)
                        .build());
    }
}
