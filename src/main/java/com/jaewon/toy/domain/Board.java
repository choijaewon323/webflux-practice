package com.jaewon.toy.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    private long id;
    private String title;
    private String content;
    private long userId;
    private long categoryId;
    private long likeCount;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Board(String title, String content, long userId, long categoryId, long likeCount) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.categoryId = categoryId;
        this.likeCount = likeCount;
    }
}
