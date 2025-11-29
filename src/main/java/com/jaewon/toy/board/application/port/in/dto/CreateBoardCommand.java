package com.jaewon.toy.board.application.port.in.dto;

import com.jaewon.toy.util.ValidationUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class CreateBoardCommand {
    private final String title;
    private final String content;
    private final String writer;
    private final String category;

    public CreateBoardCommand(String title, String content, String writer, String category) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;

        validate();
    }

    private void validate() {
        ValidationUtil.checkStringEmpty("title", title);
        ValidationUtil.checkStringEmpty("content", content);
        ValidationUtil.checkStringEmpty("writer", writer);
        ValidationUtil.checkStringEmpty("category", category);
    }
}
