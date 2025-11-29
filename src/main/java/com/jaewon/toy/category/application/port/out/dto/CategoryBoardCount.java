package com.jaewon.toy.category.application.port.out.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryBoardCount {
    private final String name;
    private final long count;
}
