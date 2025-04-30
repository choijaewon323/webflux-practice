package com.jaewon.toy.repository;

import com.jaewon.toy.domain.dto.UserRequiredResponseDto;
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
}
