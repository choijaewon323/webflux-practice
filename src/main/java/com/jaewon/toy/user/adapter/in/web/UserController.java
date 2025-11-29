package com.jaewon.toy.user.adapter.in.web;

import com.jaewon.toy.user.application.port.in.CreateUserUseCase;
import com.jaewon.toy.user.application.port.in.LoginUseCase;
import com.jaewon.toy.user.adapter.in.web.dto.LoginRequestDto;
import com.jaewon.toy.user.adapter.in.web.dto.LoginResponseDto;
import com.jaewon.toy.user.domain.dto.UserListResponseDto;
import com.jaewon.toy.user.domain.dto.UserSaveRequestDto;
import com.jaewon.toy.application.service.LogService;
import com.jaewon.toy.user.application.service.UserDeleteService;
import com.jaewon.toy.user.application.service.UserService;
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
    private final LogService logService;
    private final LoginUseCase loginUseCase;
    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/login")
    public Mono<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        request.validate();

        return loginUseCase.login(request.getEmail(), request.getPassword())
                .map(result -> new LoginResponseDto(result, request.getEmail(), request.getPassword()));
    }

    @PostMapping
    public Mono<Boolean> newUser(@RequestBody UserSaveRequestDto request) {
        request.validate();

        return createUserUseCase.create(request.getEmail(), request.getPassword(), request.getNickname())
                .doOnError(logService::saveError)
                .onErrorReturn(false);
    }

    @DeleteMapping("/{email}")
    public Mono<Boolean> delete(@PathVariable String email) {
        return userDeleteService.deleteByEmail(email)
                .doOnError(logService::saveError)
                .onErrorReturn(false);
    }

    @GetMapping("/all")
    public Mono<UserListResponseDto> getAll() {
        return userService.getAll()
                .doOnError(logService::saveError);
    }
}
