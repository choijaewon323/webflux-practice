package com.jaewon.toy.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class BoardRequiredResponseDto {
    private long id;
    private String category;
    private String title;
    private String writer;
    private long likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
