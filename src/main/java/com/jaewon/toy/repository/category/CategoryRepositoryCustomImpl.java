package com.jaewon.toy.repository.category;

import com.jaewon.toy.domain.category.dto.CategoryListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {
    private final DatabaseClient databaseClient;
    private final MappingR2dbcConverter converter;

    @Override
    public Flux<CategoryListResponseDto> getAll() {
        String sql = """
                SELECT
                    c.name AS categoryName,
                    cnt AS count
                FROM
                    categories AS c
                    LEFT JOIN (SELECT
                                    category_id,
                                    COUNT(*) AS cnt
                                FROM
                                    boards AS b
                                GROUP BY
                                    category_id) AS tmp
                """;

        return databaseClient.sql(sql)
                .map(((row, rowMetadata) -> converter.read(CategoryListResponseDto.class, row, rowMetadata)))
                .all();
    }
}
