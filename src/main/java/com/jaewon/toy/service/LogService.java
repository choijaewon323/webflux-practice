package com.jaewon.toy.service;

import com.jaewon.toy.domain.log.Log;
import com.jaewon.toy.repository.log.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class LogService {
    private final LogRepository logRepository;

    public Mono<Void> save(Throwable throwable) {
        return logRepository.save(Log.of(throwable))
                .then();
    }
}
