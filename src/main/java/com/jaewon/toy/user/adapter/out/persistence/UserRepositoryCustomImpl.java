package com.jaewon.toy.user.adapter.out.persistence;

import com.jaewon.toy.user.domain.dto.UserRequiredResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final DatabaseClient databaseClient;
    private final MappingR2dbcConverter converter;

    @Override
    public Flux<UserRequiredResponseDto> getAllUsersRequiredColumn() {
        String sql = """
                SELECT
                    id,
                    created_at,
                    nickname,
                    email
                FROM
                    users
                """;

        return databaseClient.sql(sql)
                .map((row, rowMetadata) -> converter.read(UserRequiredResponseDto.class, row, rowMetadata))
                .all();
    }

    @Override
    public Mono<Long> countAllUsers() {
        String sql = """
                SELECT
                    COUNT(*)
                FROM
                    users
                """;

        return databaseClient.sql(sql)
                .map(((row, rowMetadata) -> converter.read(Long.class, row, rowMetadata)))
                .one();
    }

    @Override
    public Mono<Void> updateNickname(String before, String after) {
        String sql = """
                UPDATE
                    users
                SET
                    nickname = :after
                where
                    nickname = :before
                """;

        return databaseClient.sql(sql)
                .bind("after", after)
                .bind("before", before)
                .then();
    }
}
