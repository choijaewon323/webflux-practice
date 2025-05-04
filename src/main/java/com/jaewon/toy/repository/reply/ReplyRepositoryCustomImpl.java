package com.jaewon.toy.repository.reply;

import com.jaewon.toy.domain.reply.dto.ReplyListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Repository
public class ReplyRepositoryCustomImpl implements ReplyRepositoryCustom {
    private final DatabaseClient databaseClient;
    private final MappingR2dbcConverter converter;

    @Override
    public Flux<ReplyListResponseDto> getAllRepliesByBoardId(long boardId) {
        String sql = """
                SELECT
                    r.id AS id,
                    content,
                    u.nickname AS writer,
                    r.created_at AS created_at,
                    r.updated_at AS updated_at
                FROM
                    replies AS r
                    INNER JOIN users AS u
                        ON r.user_id = u.id
                WHERE
                    r.board_id = :boardId
                """;

        return databaseClient.sql(sql)
                .bind("boardId", boardId)
                .map(((row, rowMetadata) -> converter.read(ReplyListResponseDto.class, row, rowMetadata)))
                .all();
    }
}
