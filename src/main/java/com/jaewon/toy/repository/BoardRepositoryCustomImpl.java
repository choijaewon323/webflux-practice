package com.jaewon.toy.repository;

import com.jaewon.toy.domain.dto.BoardRequiredResponseDto;
import com.jaewon.toy.util.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
    private final DatabaseClient databaseClient;

    @Override
    public Flux<BoardRequiredResponseDto> findAllByRequiredColumn() {
        String sql = Query.builder()
                .select("b.id as b_id, name, title, nickname, b.created_at as b_created_at, updated_at, like_count")
                .from("boards as b")
                .innerJoin("categories as c").on("b.category_id = c.id")
                .innerJoin("users as u").on("u.id = b.user_id")
                .build();

        return databaseClient.sql(sql)
                .map(readable -> BoardRequiredResponseDto.builder()
                        .id(Long.parseLong(String.valueOf(readable.get("b_id"))))
                        .title(String.valueOf(readable.get("title")))
                        .writer(String.valueOf(readable.get("nickname")))
                        .category(String.valueOf(readable.get("name")))
                        .createdAt(LocalDateTime.parse(String.valueOf(readable.get("b_created_at"))))
                        .updatedAt(LocalDateTime.parse(String.valueOf(readable.get("updated_at"))))
                        .likeCount(Long.parseLong(String.valueOf(readable.get("like_count"))))
                        .build())
                .all();
    }
}
