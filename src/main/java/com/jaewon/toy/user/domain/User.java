package com.jaewon.toy.user.domain;

import com.jaewon.toy.util.ValidationUtil;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private final Long id;
    private String nickname;
    private final String email;
    private String password;
    private final LocalDateTime createdAt;

    private User(Long id,
                String nickname,
                String email,
                String password,
                LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;

        checkNicknameUnder20(nickname);
        ValidationUtil.checkStringEmpty("password", password);
    }

    public static User newUser(String email, String password, String nickname) {
        return new User(
                null,
                nickname,
                email,
                password,
                LocalDateTime.now()
        );
    }

    public boolean isPasswordCorrect(String compare) {
        return password.equals(compare);
    }

    public void updateNickname(String newNickname) {
        checkNicknameUnder20(newNickname);

        this.nickname = newNickname;
    }

    public void updatePassword(String newPassword) {
        checkNewPassword(newPassword);

        this.password = newPassword;
    }

    private void checkNicknameUnder20(String nickname) {
        if (nickname.length() > 20) {
            throw new IllegalArgumentException("nickname length cannot over 20");
        }
    }

    private void checkNewPassword(String newPassword) {
        ValidationUtil.checkStringEmpty("newPassword", newPassword);

        if (isNewPasswordSame(newPassword)) {
            throw new IllegalArgumentException("same password");
        }
    }

    private boolean isNewPasswordSame(String newPassword) {
        return newPassword.equals(password);
    }
}
