package com.jaewon.toy.user.domain.dto;

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
