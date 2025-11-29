package com.jaewon.toy.like.domain.dto;

import com.jaewon.toy.util.ValidationUtil;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeSaveRequestDto {
    private Long targetId;
    private Long userId;

    public void validate() {
        if (targetId == null || userId == null) {
            throw new IllegalArgumentException("id is empty");
        }

        ValidationUtil.checkNumberNegative("targetId", targetId);
        ValidationUtil.checkNumberNegative("userId", userId);
    }
}
