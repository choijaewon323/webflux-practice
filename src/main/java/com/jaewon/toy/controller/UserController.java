package com.jaewon.toy.controller;

import com.jaewon.toy.domain.user.dto.UserListResponseDto;
import com.jaewon.toy.domain.user.dto.UserSaveRequestDto;
import com.jaewon.toy.service.UserDeleteService;
import com.jaewon.toy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserDeleteService userDeleteService;

    @PostMapping
    public Mono<Boolean> newUser(@RequestBody UserSaveRequestDto request) {
        return userService.save(request);
    }

    @DeleteMapping("/{email}")
    public Mono<Boolean> delete(@PathVariable String email) {
        return userDeleteService.deleteByEmail(email);
    }

    @GetMapping("/all")
    public Mono<UserListResponseDto> getAll() {
        return userService.getAll();
    }
}
