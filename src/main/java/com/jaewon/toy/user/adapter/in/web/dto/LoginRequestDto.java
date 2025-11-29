package com.jaewon.toy.user.adapter.in.web.dto;

import com.jaewon.toy.util.ValidationUtil;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    private String email;
    private String password;

    public void validate() {
        ValidationUtil.checkStringEmpty("email", email);
        ValidationUtil.checkStringEmpty("password", password);
    }
}
