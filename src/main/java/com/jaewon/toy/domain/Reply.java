package com.jaewon.toy.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("replies")
public class Reply {
    @Id
    private long id;
    private long userId;
    private long boardId;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Reply(long userId, long boardId, String content) {
        this.userId = userId;
        this.boardId = boardId;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
}
