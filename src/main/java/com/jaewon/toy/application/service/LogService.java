package com.jaewon.toy.application.service;

import com.jaewon.toy.domain.log.Log;
import com.jaewon.toy.adapter.out.persistence.log.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogService {
    private final LogRepository logRepository;

    public void saveError(Throwable throwable) {
        Mono.error(throwable)
                .doOnError(err -> log.error("error occurred : ", err))
                .then(save(throwable))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }

    private Mono<Void> save(Throwable throwable) {
        return logRepository.save(Log.of(throwable))
                .then();
    }
}
