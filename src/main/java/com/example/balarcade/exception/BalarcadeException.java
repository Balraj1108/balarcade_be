package com.example.balarcade.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BalarcadeException extends RuntimeException {

    private final String message;
    private final HttpStatus status;
    private final Object[] substitutionParameter;


    public BalarcadeException(
            String message,
            HttpStatus statusCode,
            Object... substitutionParameter) {

        this.status = statusCode;
        this.message = message;
        this.substitutionParameter = substitutionParameter;
    }
}
