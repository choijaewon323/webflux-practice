package com.jaewon.toy.board.domain;

import com.jaewon.toy.board.application.port.in.dto.CreateBoardCommand;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Board {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private Long categoryId;
    private long cnt;
    private LocalDateTime createdDate;

    public Board(Long id,
                 String title,
                 String content,
                 Long userId,
                 Long categoryId,
                 long cnt,
                 LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.categoryId = categoryId;
        this.cnt = cnt;

        checkTitleUnder200(title);
        checkContentIsEmpty(content);
    }

    public static Board newBoard(CreateBoardCommand command) {
        return new Board(
                null,
                command.getTitle(),
                command.getContent(),
                null,
                null,
                0L,
                LocalDateTime.now()
        );
    }

    public String getContentToDescription() {
        return content.substring(0, 50);
    }

    private void checkTitleUnder200(String title) {
        if (title.length() > 200) {
            throw new IllegalArgumentException("title cannot over 200 length");
        }
    }

    private void checkContentIsEmpty(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("content cannot empty");
        }
    }

    public void increaseCnt() {
        this.cnt++;
    }
}
