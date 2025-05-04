package com.jaewon.toy.service;

import com.jaewon.toy.domain.user.User;
import com.jaewon.toy.domain.user.dto.LoginRequestDto;
import com.jaewon.toy.domain.user.dto.UserListResponseDto;
import com.jaewon.toy.domain.user.dto.UserSaveRequestDto;
import com.jaewon.toy.repository.user.UserRepository;
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

    public Mono<String> findNicknameById(long userId) {
        return userRepository.findById(userId)
                .map(User::getNickname);
    }

    public Mono<Boolean> save(UserSaveRequestDto request) {
        return userRepository.findIdByEmail(request.getEmail())
                .flatMap(id -> Mono.error(new RuntimeException("이미 존재하는 이메일입니다")))
                .switchIfEmpty(userRepository.findIdByNickname(request.getNickname()))
                .flatMap(id -> Mono.error(new RuntimeException("이미 존재하는 닉네임입니다")))
                .switchIfEmpty(userRepository.save(User.builder()
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

    public Mono<Boolean> login(LoginRequestDto request) {
        return userRepository.findByEmail(request.getEmail())
                .switchIfEmpty(Mono.error(new RuntimeException("해당 이메일의 유저가 없습니다")))
                .flatMap(user -> {
                    if (!user.getPassword().equals(request.getPassword())) {
                        return Mono.error(new RuntimeException("비밀번호가 다릅니다"));
                    }
                    return Mono.just(true);
                })
                .as(operator::transactional);
    }
}
