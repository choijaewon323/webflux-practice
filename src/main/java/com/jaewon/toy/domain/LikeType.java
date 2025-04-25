package com.jaewon.toy.domain;

public enum LikeType {
    BOARD("board"),
    REPLY("reply")
    ;
    private final String type;

    LikeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
