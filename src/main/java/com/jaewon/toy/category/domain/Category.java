package com.jaewon.toy.category.domain;

import com.jaewon.toy.util.ValidationUtil;
import lombok.Getter;

@Getter
public class Category {
    private Long id;
    private String name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;

        ValidationUtil.checkStringEmpty("name", name);
        checkNameUnder30(name);
    }

    public static Category create(String name) {
        return new Category(null, name);
    }

    public static Category noCategory() {
        return new Category(null, "카테고리 없음");
    }

    private void checkNameUnder30(String name) {
        if (name.length() > 30) {
            throw new IllegalArgumentException();
        }
    }
}
