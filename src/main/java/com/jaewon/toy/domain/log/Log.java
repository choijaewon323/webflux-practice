package com.jaewon.toy.domain.log;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("logs")
public class Log {
    @Id
    private long id;
    private String exceptionMessage;
    @CreatedDate
    private LocalDateTime createdAt;

    private Log(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public static Log of(Throwable throwable) {
        return new Log(String.valueOf(throwable));
    }
}
