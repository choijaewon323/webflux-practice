package com.jaewon.toy.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
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
