package com.jaewon.toy.user.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Getter
public class LoginResponseDto {
    @JsonProperty("isSuccess")
    private final boolean isSuccess;
    private final String nickname;
    private final String email;
}
