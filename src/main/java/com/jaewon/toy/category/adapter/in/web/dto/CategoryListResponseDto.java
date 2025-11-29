package com.jaewon.toy.category.adapter.in.web.dto;

import com.jaewon.toy.util.ValidationUtil;
import lombok.*;

@Getter
public class CategoryListResponseDto {
    private long count;
    private String categoryName;

    public CategoryListResponseDto(long count, String categoryName) {
        this.count = count;
        this.categoryName = categoryName;

        validate();
    }

    private void validate() {
        ValidationUtil.checkNumberNegative("count", count);
        ValidationUtil.checkStringEmpty("categoryName", categoryName);
    }
}
