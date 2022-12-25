package com.alkafol.purchasesmicroservice.dto;

import com.alkafol.purchasesmicroservice.models.PurchaseStatus;

import java.time.LocalDateTime;

public class PurchaseGetDto {
    private final int purchaseId;
    private final String username;
    private final int productId;
    private final int amount;
    private final LocalDateTime localDateTime;
    private final double totalPrice;
    private final PurchaseStatus status;

    public PurchaseGetDto(int purchaseId, String username, int productId, int amount, LocalDateTime localDateTime, double totalPrice, PurchaseStatus status) {
        this.purchaseId = purchaseId;
        this.username = username;
        this.productId = productId;
        this.amount = amount;
        this.localDateTime = localDateTime;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public String getUsername() {
        return username;
    }

    public int getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public PurchaseStatus getStatus() {
        return status;
    }
}

