package com.alkafol.apigateway.filter;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> adminEndpoints = List.of(
            "/products/change_name/", "/products/change_description/", "/products/change_description/",
            "/products/change_organization/", "/products/change_price/", "/products/change_amount/",
            "/products/add_keywords/", "/products/remove_keywords/", "/products/add_characteristics/",
            "/products/remove_characteristics/", "/discounts/add", "/discounts/change_expiry",
            "/discounts/change_amount", "/discounts/remove_products", "/discounts/add_products",
            "/reviews/change_assessment", "/reviews/change_description", "/purchases/view_user_history",
            "/users/add_money/", "/users/change_status/", "/users/view_info/", "/notifications/send",
            "/organizations/change_status", "/products/get_by_status", "/products/get_all", "/organizations/get_all"
    );

    public static final List<String> freeEndpoints = List.of(
            "/users/register", "/users/authenticate", "/validate_user_token", "/validate_admin_token"
    );

    public Predicate<ServerHttpRequest> isAdminEndpoint =
            request -> adminEndpoints
                    .stream()
                    .anyMatch(uri -> request.getURI().getPath().contains(uri));

    public Predicate<ServerHttpRequest> isFreeEndpoint =
            request -> freeEndpoints
                    .stream()
                    .anyMatch(uri -> request.getURI().getPath().contains(uri));
}
