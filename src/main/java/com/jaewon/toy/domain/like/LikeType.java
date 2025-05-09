package com.jaewon.toy.domain.like;

import lombok.Getter;

@Getter
public enum LikeType {
    BOARD("board"),
    REPLY("reply")
    ;
    private final String type;

    LikeType(String type) {
        this.type = type;
    }
}
