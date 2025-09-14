package com.konecta.auth_service.service;

import com.konecta.auth_service.configuration.JwtService;
import com.konecta.auth_service.dto.AuthResponse;
import com.konecta.auth_service.dto.LoginRequest;
import com.konecta.auth_service.dto.SignupRequest;
import com.konecta.auth_service.entity.User;
import com.konecta.auth_service.exception.InvalidCredentialsException;
import com.konecta.auth_service.exception.UserAlreadyExistsException;
import com.konecta.auth_service.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.encoder = new BCryptPasswordEncoder();
    }

    public AuthResponse signup(SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "Username Already InUse",
                    Map.of("username", request.getUsername() + " is already in use")
            );
        }
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(user);

        AuthResponse response = new AuthResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        response.setToken(jwtService.generateToken(user.getUsername()));
        return response;
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        AuthResponse response = new AuthResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        response.setToken(jwtService.generateToken(user.getUsername()));
        return response;
    }
}
