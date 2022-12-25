package com.alkafol.apigateway;

import com.alkafol.apigateway.filter.AuthenticationFilterGatewayFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, AuthenticationFilterGatewayFilterFactory authFilter) {
        return builder.routes()
                .route(p -> p
                        .path("/products/change_name/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/products/change_description/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/products/change_organization/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/products/change_price/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/products/change_amount/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/products/add_keywords/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/products/remove_keywords/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/products/add_characteristics/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/products/remove_characteristics/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/discounts/add")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/discounts/add_products/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/discounts/remove_products/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/discounts/change_amount/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/discounts/change_expiry/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/reviews/change_description/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/reviews/change_assessment/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/purchases/make")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8082"))
                .route(p -> p
                        .path("/reviews/add")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/products/add_assessment/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/purchases/view_my_history")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8082"))
                .route(p -> p
                        .path("/purchases/view_user_history/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8082"))
                .route(p -> p
                        .path("/purchases/cancel_purchase/**" )
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8082"))
                .route(p -> p
                        .path("/users/add_money/**" )
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8085"))
                .route(p -> p
                        .path("/users/change_status/**" )
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8085"))
                .route(p -> p
                        .path("/users/view_info/**" )
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8085"))
                .route(p -> p
                        .path("/notifications/send" )
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8084"))
                .route(p -> p
                        .path("/notifications/check" )
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8084"))
                .route(p -> p
                        .path("/organizations/create" )
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8083"))
                .route(p -> p
                        .path("/organizations/change_status/**" )
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8083"))
                .route(p -> p
                        .path("/products/add")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/users/register")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8085"))
                .route(p -> p
                        .path("/users/authenticate")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8085"))
                .route(p -> p
                        .path("/users/validate_user_token/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8085"))
                .route(p -> p
                        .path("/users/validate_admin_token/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8085"))
                // my getters
                .route(p -> p
                        .path("/products/get_all")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/products/get_by_status/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/discounts/get_all")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/products/get_product_reviews/**")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/organizations/get_all")
                        .filters(f -> f
                                .filter(authFilter.apply(new AuthenticationFilterGatewayFilterFactory.Config())))
                        .uri("http://localhost:8083"))
                .build();

    }
}
