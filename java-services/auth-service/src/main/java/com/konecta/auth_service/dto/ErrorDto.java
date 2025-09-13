package com.konecta.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ErrorDto {
    private String status;
    private String message;
    private String path;
    private Map<String, String> errors;
    private LocalDateTime timestamp;

    public ErrorDto(String status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = new HashMap<>();
        this.timestamp = LocalDateTime.now();
    }
}
