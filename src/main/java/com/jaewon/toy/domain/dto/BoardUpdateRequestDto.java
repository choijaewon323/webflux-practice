package com.jaewon.toy.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardUpdateRequestDto {
    private String title;
    private String content;
}
