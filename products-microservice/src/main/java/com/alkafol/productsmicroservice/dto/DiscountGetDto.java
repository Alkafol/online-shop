package com.alkafol.productsmicroservice.dto;

import java.time.LocalDate;
import java.util.List;

public class DiscountGetDto {
    private final int discountId;
    private final List<Integer> productsId;
    private final LocalDate expiryDate;
    private final double amount;

    public DiscountGetDto(int discountId, List<Integer> productsId, LocalDate expiryDate, double amount) {
        this.discountId = discountId;
        this.productsId = productsId;
        this.expiryDate = expiryDate;
        this.amount = amount;
    }

    public int getDiscountId() {
        return discountId;
    }

    public List<Integer> getProductsId() {
        return productsId;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public double getAmount() {
        return amount;
    }
}
