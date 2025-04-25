package com.jaewon.toy.domain.dto;

import com.jaewon.toy.domain.Board;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardDetailResponseDto toDto(Board board) {
        return BoardDetailResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .category(board.getCategory())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .likeCount(board.getLikeCount())
                .build();
    }
}
