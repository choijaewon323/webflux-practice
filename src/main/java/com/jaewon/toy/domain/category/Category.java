package com.jaewon.toy.domain.category;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("categories")
public class Category {
    @Id
    private long id;
    private String name;

    @Builder
    public Category(String name) {
        this.name = name;
    }
}
