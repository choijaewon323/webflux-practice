package com.jaewon.toy.user.adapter.in.web.dto;

import com.jaewon.toy.util.ValidationUtil;
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

    public void validate() {
        ValidationUtil.checkStringEmpty("nickname", nickname);
        ValidationUtil.checkStringEmpty("email", email);
        ValidationUtil.checkStringEmpty("password", password);
    }
}
