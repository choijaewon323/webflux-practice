package com.jaewon.toy.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("boards")
public class Board {
    @Id
    private long id;
    private String title;
    private String content;
    private long userId;
    private long categoryId;
    private long cnt;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Board(String title, String content, long userId, long categoryId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public void increaseCnt() {
        this.cnt++;
    }
}
