package com.jaewon.toy.reply.adapter.in.web.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ReplyListResponseDto {
    private final long id;
    private final String writer;
    private final String content;
    private final String createdAt;

    public ReplyListResponseDto(long id,
                                String writer,
                                String content,
                                LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
