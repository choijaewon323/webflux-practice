package com.jaewon.toy.domain.category.dto;

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
