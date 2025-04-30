package com.jaewon.toy.domain.board.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardListResponseDto {
    private long count;
    private List<BoardRequiredResponseDto> boardList;
}
