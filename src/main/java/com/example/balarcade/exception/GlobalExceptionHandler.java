package com.example.balarcade.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(BalarcadeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidLoginException(BalarcadeException ex) {
        // Log l'errore
        logger.error("BalarcadeException {}", ex.getMessage());
        return ResponseEntity.status(ex.getStatus())
                .body(Collections.singletonMap("error", ex.getMessage()));
    }
}

