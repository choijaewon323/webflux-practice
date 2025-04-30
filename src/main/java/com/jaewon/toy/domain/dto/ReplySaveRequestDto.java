package com.jaewon.toy.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplySaveRequestDto {
    private String nickname;
    private String content;
    private long boardId;
}
