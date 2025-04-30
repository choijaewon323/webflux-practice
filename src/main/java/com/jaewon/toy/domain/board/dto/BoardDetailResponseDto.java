package com.jaewon.toy.domain.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDetailResponseDto {
    private long id;
    private String title;
    private String content;
    private String writer;
    private String category;
    private long likeCount;
    private long cnt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
