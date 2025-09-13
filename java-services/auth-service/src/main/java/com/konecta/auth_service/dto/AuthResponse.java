package com.konecta.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private Long id;
    private String name;
    private String username;
    private String token;
}
