package com.jaewon.toy.domain.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequiredResponseDto {
    private long id;
    private String category;
    private String title;
    private String writer;
    private long likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
