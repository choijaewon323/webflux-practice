package com.jaewon.toy.board.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaewon.toy.util.ValidationUtil;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardMinimumFieldResponse {
    private long count;
    private List<BoardMinimumField> boardList;

    public static class BoardMinimumField {
        private long id;
        private String category;
        private String title;
        private String writer;
        @JsonProperty("content")
        private String description;
        private long likeCount;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdAt;

        public BoardMinimumField(long id,
                                 String category,
                                 String title,
                                 String writer,
                                 String description,
                                 long likeCount,
                                 LocalDateTime createdAt) {
            this.id = id;
            this.category = category;
            this.title = title;
            this.writer = writer;
            this.description = description;
            this.likeCount = likeCount;
            this.createdAt = createdAt;

            validate();
        }

        private void validate() {
            ValidationUtil.checkStringEmpty("title", title);
            ValidationUtil.checkStringEmpty("description", description);
            ValidationUtil.checkStringEmpty("title", title);
            ValidationUtil.checkNumberNegative("likeCount", likeCount);
        }
    }

    public BoardMinimumFieldResponse(List<BoardMinimumField> boardList) {
        this.boardList = boardList;
        this.count = boardList.size();
    }
}
