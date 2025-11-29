package com.jaewon.toy.board.adapter.out.persistence;

import com.jaewon.toy.domain.boardJpaEntity.dto.BoardRequiredResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
    private final DatabaseClient databaseClient;
    private final MappingR2dbcConverter converter;

    @Override
    public Flux<BoardRequiredResponseDto> findAllByRequiredColumn() {
        String sql = """
                SELECT
                    b.id AS id,
                    c.name AS category,
                    title,
                    content,
                    nickname as writer,
                    b.created_at as created_at,
                    updated_at,
                    tmp.cnt AS like_count
                FROM
                    boards AS b
                    INNER JOIN categories AS c
                        ON b.category_id = c.id
                    INNER JOIN users AS u
                        ON u.id = b.user_id
                    LEFT JOIN (SELECT
                                    target_id, COUNT(*) as cnt
                                FROM
                                    likes
                                WHERE
                                    like_type = 'boardJpaEntity'
                                GROUP BY
                                    target_id) as tmp
                        ON tmp.target_id = b.id
                """;

        return databaseClient.sql(sql)
                .map((row, rowMetadata) -> converter.read(BoardRequiredResponseDto.class, row, rowMetadata))
                .all();
    }

    @Override
    public Flux<BoardRequiredResponseDto> findAllByCategoryIdRequiredColumn(long categoryId) {
        String sql = """
                SELECT
                    b.id AS id,
                    c.name AS category,
                    title,
                    nickname as writer,
                    b.created_at as created_at,
                    updated_at,
                    tmp.cnt AS like_count
                FROM
                    boards AS b
                    INNER JOIN categories AS c
                        ON b.category_id = c.id
                    INNER JOIN users AS u
                        ON u.id = b.user_id
                    LEFT JOIN (SELECT
                                    target_id, COUNT(*) as cnt
                                FROM
                                    likes
                                WHERE
                                    like_type = 'boardJpaEntity'
                                GROUP BY
                                    target_id) as tmp
                        ON tmp.target_id = b.id
                WHERE
                    category_id = :categoryId
                """;

        return databaseClient.sql(sql)
                .bind("categoryId", categoryId)
                .map((row, rowMetadata) -> converter.read(BoardRequiredResponseDto.class, row, rowMetadata))
                .all();
    }

    @Override
    public Flux<BoardRequiredResponseDto> findAllByKeywordContains(String keyword) {
        String sql = """
                SELECT
                    b.id AS id,
                    c.name AS category,
                    title,
                    nickname as writer,
                    b.created_at as created_at,
                    updated_at,
                    tmp.cnt AS like_count
                FROM
                    boards AS b
                    INNER JOIN categories AS c
                        ON b.category_id = c.id
                    INNER JOIN users AS u
                        ON u.id = b.user_id
                    LEFT JOIN (SELECT
                                    target_id, COUNT(*) AS cnt
                                FROM
                                    likes
                                WHERE
                                    like_type = 'boardJpaEntity'
                                GROUP BY
                                    target_id) AS tmp
                        ON tmp.target_id = b.id
                where
                    b.title like :keyword OR
                    b.content like :keyword OR
                    u.nickname like :keyword
                """;

        return databaseClient.sql(sql)
                .bind("keyword", "%" + keyword + "%")
                .map((row, rowMetadata) -> converter.read(BoardRequiredResponseDto.class, row, rowMetadata))
                .all();
    }
}
