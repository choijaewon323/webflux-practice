package com.jaewon.toy.user.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPasswordUpdateRequestDto {
    private String email;
    private String oldPassword;
    private String newPassword;
}
