package com.jaewon.toy.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BoardSaveRequestDto {
    private String title;
    private String content;
    private String writer;
    private String category;
}
