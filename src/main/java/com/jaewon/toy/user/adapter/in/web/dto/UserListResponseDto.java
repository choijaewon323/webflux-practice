package com.jaewon.toy.user.adapter.in.web.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class UserListResponseDto {
    private final long count;
    private final List<UserMinimumResponse> users;

    public UserListResponseDto(List<UserMinimumResponse> users) {
        this.users = users;
        this.count = users.size();
    }

    public static class UserMinimumResponse {
        private final long id;
        private final String email;
        private final String createdAt;
        private final String nickname;

        public UserMinimumResponse(long id, String email, LocalDateTime createdAt, String nickname) {
            this.id = id;
            this.email = email;
            this.createdAt = createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            this.nickname = nickname;
        }
    }
}
