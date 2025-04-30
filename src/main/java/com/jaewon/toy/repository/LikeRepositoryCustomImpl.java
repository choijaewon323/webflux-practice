package com.jaewon.toy.repository;

import com.jaewon.toy.domain.LikeType;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class LikeRepositoryCustomImpl implements LikeRepositoryCustom {
    private final DatabaseClient databaseClient;

    @Override
    public Mono<Boolean> deleteByTypeAndTargetId(LikeType likeType, long targetId) {
        String sql = """
                DELETE FROM
                    likes
                WHERE
                    like_type = :type AND
                    target_id = :targetId
                """;

        return databaseClient.sql(sql)
                .bind("type", likeType.getType())
                .bind("targetId", targetId)
                .then()
                .thenReturn(true);
    }

    @Override
    public Mono<Long> countByTypeAndTargetId(LikeType likeType, long targetId) {
        String sql = """
                SELECT
                    COUNT(*)
                FROM
                    likes
                WHERE
                    like_type = :type AND
                    target_id = :targetId
                """;

        return databaseClient.sql(sql)
                .bind("type", likeType.getType())
                .bind("targetId", targetId)
                .map(readable -> Long.parseLong(String.valueOf(readable.get(0))))
                .one();
    }

    @Override
    public Mono<Boolean> isLiked(long userId, long targetId, LikeType likeType) {
        String sql = """
                SELECT
                    COUNT(*)
                FROM
                    likes
                WHERE
                    user_id = :userId AND
                    targetId = :targetId AND
                    like_type = :type
                """;

        return databaseClient.sql(sql)
                .bind("userId", userId)
                .bind("targetId", targetId)
                .bind("type", likeType.getType())
                .map(readable -> {
                    long count = Long.parseLong(String.valueOf(readable.get(0)));
                    return (count > 0);
                })
                .one();
    }

    @Override
    public Mono<Boolean> deleteByTypeAndTargetIdAndUserId(LikeType likeType, long targetId, long userId) {
        String sql = """
                DELETE FROM
                    likes
                WHERE
                    like_type = :type AND
                    target_id = :targetId AND
                    user_id = :userId
                """;

        return databaseClient.sql(sql)
                .bind("type", likeType.getType())
                .bind("target_id", targetId)
                .bind("user_id", userId)
                .then()
                .thenReturn(true);
    }
}
