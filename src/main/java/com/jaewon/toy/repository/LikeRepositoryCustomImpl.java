package com.jaewon.toy.repository;

import com.jaewon.toy.domain.LikeType;
import com.jaewon.toy.util.Query;
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
        String sql = Query.builder()
                .deleteFrom("likes")
                .where("type").eq(likeType.getType())
                .and("target_id").eq(String.valueOf(targetId))
                .build();

        return databaseClient.sql(sql)
                .then()
                .thenReturn(true);
    }

    @Override
    public Mono<Long> countByTypeAndTargetId(LikeType likeType, long targetId) {
        String sql = Query.builder()
                .select("count(*)")
                .from("likes")
                .where("type").eq("'" + likeType.getType() + "'")
                .and("target_id").eq(String.valueOf(targetId))
                .build();

        return databaseClient.sql(sql)
                .map(readable -> Long.parseLong(String.valueOf(readable.get(0))))
                .one();
    }

    @Override
    public Mono<Boolean> isLiked(long userId, long targetId, LikeType likeType) {
        String sql = Query.builder()
                .select("count(*)")
                .from("likes")
                .where("user_id").eq(String.valueOf(userId))
                .and("targetId").eq(String.valueOf(targetId))
                .and("type").eq("'" + likeType.getType() + "'")
                .build();

        return databaseClient.sql(sql)
                .map(readable -> {
                    long count = Long.parseLong(String.valueOf(readable.get(0)));

                    return (count > 0);
                })
                .one();
    }

    @Override
    public Mono<Boolean> deleteByTypeAndTargetIdAndUserId(LikeType likeType, long targetId, long userId) {
        String sql = Query.builder()
                .deleteFrom("likes")
                .where("type").eq("'" + likeType.getType() + "'")
                .and("target_id").eq(String.valueOf(targetId))
                .and("user_id").eq(String.valueOf(userId))
                .build();

        return databaseClient.sql(sql)
                .then()
                .thenReturn(true);
    }
}
