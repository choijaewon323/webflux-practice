package com.jaewon.toy.like.domain;

import lombok.Getter;

public class Like {
    @Getter private Long id;
    private LikeType type;
    @Getter private long userId;
    @Getter private long targetId;
    private boolean isLike;

    public Like(Long id,
                LikeType type,
                Long userId,
                Long targetId,
                boolean isLike) {
        this.id = id;
        this.type = type;
        this.userId = userId;
        this.targetId = targetId;
        this.isLike = isLike;

        checkIdNotNull(userId);
        checkIdNotNull(targetId);
    }

    public void like() {
        isLike = true;
    }

    public void dislike() {
        isLike = false;
    }

    public static Like createBoardLike(Long userId, Long targetId) {
        return new Like(
                null,
                LikeType.BOARD,
                userId,
                targetId,
                true
        );
    }

    public static Like createReplyLike(Long userId, Long targetId) {
        return new Like(
                null,
                LikeType.REPLY,
                userId,
                targetId,
                true
        );
    }

    private void checkIdNotNull(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("wrong like operation");
        }
    }
}
