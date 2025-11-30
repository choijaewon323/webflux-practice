package com.jaewon.toy.reply.adapter.in.web.dto;

import com.jaewon.toy.util.ValidationUtil;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReplySaveRequestDto {
    private String content;
    private Long boardId;
    private Long userId;

    public void validate() {
        ValidationUtil.checkNull("boardId", boardId);
        ValidationUtil.checkNull("userId", userId);
        ValidationUtil.checkNull("content", content);
    }
}
