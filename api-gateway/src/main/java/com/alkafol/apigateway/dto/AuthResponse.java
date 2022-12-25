package com.alkafol.apigateway.dto;

public class AuthResponse {
    private String result;
    private String username;

    public AuthResponse(String result, String username) {
        this.result = result;
        this.username = username;
    }

    public AuthResponse() {
    }

    public String getResult() {
        return result;
    }

    public String getUsername() {
        return username;
    }
}
