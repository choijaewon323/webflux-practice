package com.jaewon.toy.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {
    @JsonProperty("isSuccess")
    private boolean isSuccess;
    private String nickname;
    private String email;
}
