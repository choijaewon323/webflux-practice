package com.jaewon.toy.service;

import com.jaewon.toy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BoardService boardService;

    public Mono<Long> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .flatMap(id -> {
                    if (id == null) {
                        return Mono.error(new RuntimeException("해당 닉네임의 유저가 없음"));
                    }
                    return Mono.just(id);
                });
    }

    public Mono<Boolean> deleteById(long userId) {
        return userRepository.deleteById(userId)
                .then(boardService.deleteAllByUserId(userId));
    }
}
