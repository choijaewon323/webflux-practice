package com.jaewon.toy.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("likes")
public class Like {
    @Id
    private long id;
    private LikeType likeType;
    private long userId;
    private long targetId;

    @Builder
    public Like(LikeType likeType, long userId, long targetId) {
        this.likeType = likeType;
        this.targetId = targetId;
        this.userId = userId;
    }
}
