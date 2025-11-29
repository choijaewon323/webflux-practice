package com.jaewon.toy.user.application.service;

import com.jaewon.toy.user.adapter.out.persistence.UserJpaEntity;
import com.jaewon.toy.user.domain.dto.LoginRequestDto;
import com.jaewon.toy.user.domain.dto.LoginResponseDto;
import com.jaewon.toy.user.domain.dto.UserListResponseDto;
import com.jaewon.toy.user.domain.dto.UserSaveRequestDto;
import com.jaewon.toy.user.adapter.out.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final TransactionalOperator operator;

    public Mono<Long> findByNickname(String nickname) {
        return userRepository.findIdByNickname(nickname)
                .switchIfEmpty(Mono.error(new RuntimeException("해당 닉네임의 유저가 없습니다")))
                .flatMap(Mono::just);
    }

    public Mono<Long> findByEmail(String email) {
        return userRepository.findIdByEmail(email)
                .switchIfEmpty(Mono.error(new RuntimeException("해당 이메일의 유저가 없습니다")))
                .flatMap(Mono::just);
    }

    public Mono<Boolean> save(UserSaveRequestDto request) {
        return userRepository.findIdByEmail(request.getEmail())
                .flatMap(id -> Mono.error(new RuntimeException("이미 존재하는 이메일입니다")))
                .switchIfEmpty(userRepository.findIdByNickname(request.getNickname()))
                .flatMap(id -> Mono.error(new RuntimeException("이미 존재하는 닉네임입니다")))
                .switchIfEmpty(userRepository.save(UserJpaEntity.builder()
                                .email(request.getEmail())
                                .nickname(request.getNickname())
                                .password(request.getPassword())
                                .build()))
                .thenReturn(true)
                .as(operator::transactional);
    }

    public Mono<Boolean> deleteByUserId(long userId) {
        return userRepository.deleteById(userId)
                .thenReturn(true);
    }

    public Mono<UserListResponseDto> getAll() {
        return userRepository.getAllUsersRequiredColumn()
                .collectList()
                .map(list -> {
                    int count = list.size();
                    return UserListResponseDto.builder()
                            .count(count)
                            .users(list)
                            .build();
                });
    }

    public Mono<LoginResponseDto> login(LoginRequestDto request) {
        return userRepository.findByEmail(request.getEmail())
                .switchIfEmpty(Mono.error(new RuntimeException("해당 이메일의 유저가 없습니다")))
                .flatMap(userJpaEntity -> {
                    if (!userJpaEntity.getPassword().equals(request.getPassword())) {
                        return Mono.error(new RuntimeException("비밀번호가 다릅니다"));
                    }

                    return Mono.just(LoginResponseDto.builder()
                            .isSuccess(true)
                            .email(userJpaEntity.getEmail())
                            .nickname(userJpaEntity.getNickname())
                            .build());
                })
                .as(operator::transactional);
    }
}
