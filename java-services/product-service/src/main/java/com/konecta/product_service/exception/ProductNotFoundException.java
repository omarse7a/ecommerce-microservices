package com.konecta.product_service.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private final Map<String, String> errors;
    public ProductNotFoundException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
