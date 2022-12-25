package com.alkafol.apigateway.filter;

import com.alkafol.apigateway.dto.AuthResponse;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.naming.AuthenticationException;

// ADMIN - eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiJyb21hbiIsImlhdCI6MTY3OTcwMzQwMywiZXhwIjoxNjc5Nzg5ODAzfQ.so4GWoTeLUWPQEF2R5Suyj0ebbq_t7d9OKXUH6R3a1Y

// USER - eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6InVzZXIiLCJpYXQiOjE2Nzk3MDM0MTgsImV4cCI6MTY3OTc4OTgxOH0.TYC5nT2kW5PLe4EJA3grGBRFG2mdwzEbT9WMEYvdcwc

@Component
public class AuthenticationFilterGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationFilterGatewayFilterFactory.Config> {
    private RouteValidator routeValidator;
    private RestTemplate restTemplate;

    public AuthenticationFilterGatewayFilterFactory(RouteValidator routeValidator, RestTemplate restTemplate){
        super(Config.class);
        this.routeValidator = routeValidator;
        this.restTemplate = restTemplate;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (!routeValidator.isFreeEndpoint.test(exchange.getRequest())){
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("No authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                }

                testToken(authHeader, exchange.getRequest());
            }

            return chain.filter(exchange);
        });
    }

    private void testToken(String token, ServerHttpRequest request){
        try{
            AuthResponse response;
            if (routeValidator.isAdminEndpoint.test(request)){
                response = restTemplate.getForObject("http://localhost:8085/users/validate_admin_token/" + token, AuthResponse.class);
            }
            else{
                response = restTemplate.getForObject("http://localhost:8085/users/validate_user_token/" + token, AuthResponse.class);
            }

            request.mutate().header("caller_username", response.getUsername());
            if (response.getResult().equals("false")){
                throw new RuntimeException("Authorization failed");
            }
        } catch (Exception e){
            throw new RuntimeException("Authentication failed");
        }
    }

    public static class Config{
    }
}
