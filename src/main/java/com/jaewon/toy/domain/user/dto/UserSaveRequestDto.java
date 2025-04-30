package com.jaewon.toy.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String nickname;
    private String email;
    private String password;
}
