package com.jaewon.toy.user.application.service;

import com.jaewon.toy.user.application.port.in.CreateUserUseCase;
import com.jaewon.toy.user.application.port.in.LoginUseCase;
import com.jaewon.toy.user.application.port.out.CreateUserPort;
import com.jaewon.toy.user.application.port.out.GetUserPort;
import com.jaewon.toy.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService implements LoginUseCase, CreateUserUseCase {
    private final GetUserPort getUserPort;
    private final CreateUserPort createUserPort;

    @Override
    public Mono<Boolean> login(String email, String password) {
        return getUserPort.getUserByEmail(email)
                .flatMap(user -> {
                    if (user.isPasswordCorrect(password)) {
                        return Mono.just(true);
                    }

                    return Mono.empty();
                })
                .defaultIfEmpty(false);
    }

    @Override
    public Mono<Boolean> create(String email, String password, String nickname) {
        return getUserPort.getUserByEmail(email)
                .flatMap(user -> Mono.error(new IllegalArgumentException("user already exists")))
                .switchIfEmpty(Mono.defer(() -> {
                    User user = User.newUser(email, password, nickname);

                    return createUserPort.save(user);
                }))
                .thenReturn(true)
                .onErrorReturn(false);
    }
}
