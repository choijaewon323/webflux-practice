package com.jaewon.toy.reply.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Reply {
    private Long id;
    private long userId;
    private long boardId;
    private String content;
    private LocalDateTime createdAt;

    private Reply(Long id,
                 long userId,
                 long boardId,
                 String content,
                 LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.boardId = boardId;
        this.content = content;
        this.createdAt = createdAt;

        checkContentUnder1000(content);
    }

    public static Reply newReply(String content, long userId, long boardId) {
        return new Reply(
                null,
                userId,
                boardId,
                content,
                LocalDateTime.now()
        );
    }

    private void checkContentUnder1000(String content) {
        if (content.length() > 1000) {
            throw new IllegalArgumentException("reply content length cannot over 1000");
        }
    }
}
