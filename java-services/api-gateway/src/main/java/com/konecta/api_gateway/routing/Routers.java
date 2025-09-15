package com.konecta.api_gateway.routing;

import com.konecta.api_gateway.security.JwtGatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
public class Routers {
    private final JwtGatewayFilter jwtFilter;

    public Routers(JwtGatewayFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public RouterFunction<ServerResponse> authServiceRouter() {
        return route("auth-service")
                .route(path("api/v1/auth/**"), http())
                .before(uri("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceRouter() {
        return route("product-service")
                .route(path("api/v1/products/**"), http())
                .before(uri("http://localhost:8082"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> cartServiceRouter() {
        return route("cart-service")
                .route(path("api/v1/cart/**"), http())
                .before(uri("http://localhost:5147"))
                .filter(jwtFilter)
                .build();
    }

}
