package com.jaewon.toy.reply.adapter.out.persistence;

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
@Table("replies")
public class ReplyJpaEntity {
    @Id
    private long id;
    private long userId;
    private long boardId;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public ReplyJpaEntity(long userId, long boardId, String content) {
        this.userId = userId;
        this.boardId = boardId;
        this.content = content;
    }
}
