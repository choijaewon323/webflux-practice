package com.jaewon.toy.domain.user.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNicknameUpdateRequestDto {
    private String before;
    private String after;
}
