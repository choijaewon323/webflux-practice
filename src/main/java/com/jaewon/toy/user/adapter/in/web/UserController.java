package com.jaewon.toy.user.adapter.in.web;

import com.jaewon.toy.user.adapter.in.web.dto.LoginRequestDto;
import com.jaewon.toy.user.adapter.in.web.dto.LoginResponseDto;
import com.jaewon.toy.user.application.port.in.CreateUserUseCase;
import com.jaewon.toy.user.application.port.in.DeleteUserByEmailUseCase;
import com.jaewon.toy.user.application.port.in.GetUserQuery;
import com.jaewon.toy.user.application.port.in.LoginUseCase;
import com.jaewon.toy.user.adapter.in.web.dto.UserListResponseDto;
import com.jaewon.toy.user.adapter.in.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final GetUserQuery getUserQuery;
    private final DeleteUserByEmailUseCase deleteUserByEmailUseCase;
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
                .onErrorReturn(false);
    }

    @DeleteMapping("/{email}")
    public Mono<Boolean> delete(@PathVariable String email) {
        return deleteUserByEmailUseCase.delete(email)
                .onErrorReturn(false);
    }

    @GetMapping("/all")
    public Mono<UserListResponseDto> getAll() {
        return getUserQuery.getUsers()
                .map(user -> new UserListResponseDto.UserMinimumResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getCreatedAt(),
                        user.getNickname()
                ))
                .collectList()
                .map(UserListResponseDto::new);
    }
}
