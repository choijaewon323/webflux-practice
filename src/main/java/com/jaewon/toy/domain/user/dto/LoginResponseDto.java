package com.jaewon.toy.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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
