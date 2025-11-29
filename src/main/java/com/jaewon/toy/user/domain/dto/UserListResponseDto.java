package com.jaewon.toy.user.domain.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListResponseDto {
    private long count;
    private List<UserRequiredResponseDto> users;
}
