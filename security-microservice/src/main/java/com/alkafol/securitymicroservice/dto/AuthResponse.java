package com.alkafol.securitymicroservice.dto;

public class AuthResponse {
    private final String result;
    private final String username;

    public AuthResponse(String result, String username) {
        this.result = result;
        this.username = username;
    }

    public String getResult() {
        return result;
    }

    public String getUsername() {
        return username;
    }
}
