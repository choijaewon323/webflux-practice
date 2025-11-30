package com.jaewon.toy.board.domain;

import com.jaewon.toy.board.application.port.in.dto.CreateBoardCommand;
import com.jaewon.toy.util.ValidationUtil;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Board {
    private final Long id;
    private String title;
    private String content;
    private final Long userId;
    private Long categoryId;
    private long cnt;
    private final LocalDateTime createdDate;

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
        this.createdDate = createdDate;

        checkTitleUnder200(title);
        ValidationUtil.checkStringEmpty("content", content);
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

    public void updateTitle(String title) {
        ValidationUtil.checkStringEmpty("title", title);
        checkTitleUnder200(title);

        this.title = title;
    }

    public void updateContent(String content) {
        ValidationUtil.checkStringEmpty("content", content);

        this.content = content;
    }

    public void changeCategory(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getContentToDescription() {
        return content.substring(0, 50);
    }

    private void checkTitleUnder200(String title) {
        if (title.length() > 200) {
            throw new IllegalArgumentException("title cannot over 200 length");
        }
    }

    public void increaseCnt() {
        this.cnt++;
    }
}
