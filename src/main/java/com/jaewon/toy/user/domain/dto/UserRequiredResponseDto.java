package com.jaewon.toy.user.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequiredResponseDto {
    private long id;
    private String email;
    private LocalDateTime createdAt;
    private String nickname;
}
