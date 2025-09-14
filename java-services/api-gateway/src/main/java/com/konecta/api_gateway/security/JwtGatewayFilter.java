package com.konecta.api_gateway.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component("JwtGatewayFilter")
public class JwtGatewayFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {
    private final JwtUtil jwtUtil;

    public JwtGatewayFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public @NonNull ServerResponse filter(ServerRequest request, @NonNull HandlerFunction<ServerResponse> next) throws Exception {
        String authHeader = request.headers().firstHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized("No token is provided", request);
        }

        String token = authHeader.substring(7);

        try {
            String username = jwtUtil.extractUsername(token);
            jwtUtil.validateToken(token);
            // add claims to headers for downstream services
            ServerRequest mutatedRequest = ServerRequest.from(request)
                    .header("X-Auth-Username", username)
                    .build();

            return next.handle(mutatedRequest);
        }
        catch (ExpiredJwtException ex) {
            return unauthorized("JWT token has expired", request);
        } catch (MalformedJwtException | SignatureException ex) {
            return unauthorized("JWT token is invalid", request);
        } catch (JwtException ex) {
            return unauthorized("JWT error: " + ex.getMessage(), request);
        }
    }

    private ServerResponse unauthorized(String message, ServerRequest request) {
        ErrorDto error = new ErrorDto(
                "Unauthorized",
                message,
                request.path()
        );
        return ServerResponse.status(401).body(error);
    }
}

