package com.jaewon.toy.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryListResponseDto {
    private long count;
    private String categoryName;
}
