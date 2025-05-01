package com.jaewon.toy.filter;

import com.jaewon.toy.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(-1)
public class ErrorWebFilter implements WebFilter {
    private final LogService logService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
                .doOnError(throwable -> {
                    log.error("error occurred :", throwable);
                    logService.save(throwable)
                            .subscribeOn(Schedulers.boundedElastic())
                            .subscribe();
                });
    }
}
