package com.jaewon.toy.category.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("categories")
public class CategoryJpaEntity {
    @Id
    private long id;
    private String name;

    @Builder
    public CategoryJpaEntity(String name) {
        this.name = name;
    }
}
