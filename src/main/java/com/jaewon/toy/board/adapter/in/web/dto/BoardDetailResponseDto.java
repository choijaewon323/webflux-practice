package com.jaewon.toy.board.adapter.in.web.dto;

import com.jaewon.toy.util.ValidationUtil;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class BoardDetailResponseDto {
    private long id;
    private String title;
    private String content;
    private String writer;
    private String category;
    private long likeCount;
    private long cnt;
    private String createdAt;

    public BoardDetailResponseDto(long id,
                                  String title,
                                  String content,
                                  String writer,
                                  String category,
                                  long likeCount,
                                  long cnt,
                                  LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;
        this.likeCount = likeCount;
        this.cnt = cnt;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        validate();
    }

    public void validate() {
        ValidationUtil.checkNumberNegative("id", id);
        ValidationUtil.checkStringEmpty("title", title);
        ValidationUtil.checkStringEmpty("content", content);
        ValidationUtil.checkStringEmpty("writer", writer);
        ValidationUtil.checkStringEmpty("category", category);
        ValidationUtil.checkNumberNegative("likeCount", likeCount);
        ValidationUtil.checkNumberNegative("cnt", cnt);
    }
}
