package com.jaewon.toy.adapter.out.persistence.log;

import com.jaewon.toy.domain.log.Log;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LogRepository extends ReactiveCrudRepository<Log, Long> {
}
