package com.konecta.auth_service.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class UserAlreadyExistsException extends RuntimeException {
    private final Map<String, String> errors;
    public UserAlreadyExistsException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
