package com.jaewon.toy.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeSaveRequestDto {
    private long targetId;
    private String nickname;
}
