package com.jaewon.toy.repository.log;

import com.jaewon.toy.domain.log.Log;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LogRepository extends ReactiveCrudRepository<Log, Long> {
}
