package com.alkafol.productsmicroservice.dto;

import java.time.LocalDate;

public class DiscountPostDto {
    private final LocalDate expiryDate;
    private final double amount;

    public DiscountPostDto(LocalDate expiryDate, double amount) {
        this.expiryDate = expiryDate;
        this.amount = amount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public double getAmount() {
        return amount;
    }
}
