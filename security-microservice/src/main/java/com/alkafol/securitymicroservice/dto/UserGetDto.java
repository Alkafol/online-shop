package com.alkafol.securitymicroservice.dto;

import com.alkafol.securitymicroservice.models.Role;
import com.alkafol.securitymicroservice.models.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserGetDto {
    private final String username;
    private final String email;
    private final double balance;
    private final UserStatus status;
    private final Role role;

    public UserGetDto(String username, String email, double balance, UserStatus status, Role role) {
        this.username = username;
        this.email = email;
        this.balance = balance;
        this.status = status;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public UserStatus getStatus() {
        return status;
    }

    public Role getRole() {
        return role;
    }
}
