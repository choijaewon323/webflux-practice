package com.jaewon.toy.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtil {
    public static void checkStringEmpty(String fieldName, String value) {
        if (value == null || value.isBlank()) {
            throwWhenViolate(fieldName);
        }
    }

    public static void checkNumberNegative(String fieldName, int number) {
        if (number < 0) {
            throwWhenViolate(fieldName);
        }
    }

    public static void checkNumberNegative(String fieldName, long number) {
        if (number < 0) {
            throwWhenViolate(fieldName);
        }
    }

    private static void throwWhenViolate(String fieldName) {
        throw new IllegalArgumentException(fieldName + " is empty");
    }

    public static <T> void checkNull(String fieldName, T value) {
        if (value == null) {
            throwWhenViolate(fieldName);
        }
    }
}
