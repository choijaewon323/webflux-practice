package com.jaewon.toy.service;

import com.jaewon.toy.domain.User;
import com.jaewon.toy.domain.dto.UserSaveRequestDto;
import com.jaewon.toy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Mono<Long> findByNickname(String nickname) {
        return userRepository.findIdByNickname(nickname)
                .switchIfEmpty(Mono.error(new RuntimeException("해당 닉네임의 유저가 없음")))
                .flatMap(Mono::just);
    }

    public Mono<Long> findByEmail(String email) {
        return userRepository.findIdByEmail(email)
                .switchIfEmpty(Mono.error(new RuntimeException("해당 이메일의 유저가 없음")))
                .flatMap(Mono::just);
    }

    public Mono<String> findNicknameById(long userId) {
        return userRepository.findById(userId)
                .map(User::getNickname);
    }

    public Mono<Boolean> save(UserSaveRequestDto request) {
        return userRepository.save(User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build())
                .thenReturn(true);
    }

    public Mono<Boolean> deleteByUserId(long userId) {
        return userRepository.deleteById(userId)
                .thenReturn(true);
    }
}
