package com.konecta.product_service.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ProductAlreadyExistsException extends RuntimeException {
    private final Map<String, String> errors;
    public ProductAlreadyExistsException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
