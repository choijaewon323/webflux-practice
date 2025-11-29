package com.jaewon.toy.like.adapter.out.persistence;

import com.jaewon.toy.like.domain.LikeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("likes")
public class LikeJpaEntity {
    @Id
    private long id;
    private LikeType likeType;
    private long userId;
    private long targetId;

    @Builder
    public LikeJpaEntity(LikeType likeType, long userId, long targetId) {
        this.likeType = likeType;
        this.targetId = targetId;
        this.userId = userId;
    }
}
