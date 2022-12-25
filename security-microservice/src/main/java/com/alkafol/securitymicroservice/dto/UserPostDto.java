package com.alkafol.securitymicroservice.dto;

import com.alkafol.securitymicroservice.models.Role;

public class UserPostDto {
    private final String username;
    private final String email;
    private final String password;
    private final Role role;

    public UserPostDto(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
