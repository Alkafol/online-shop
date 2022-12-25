package com.alkafol.purchasesmicroservice.dto;

public class PurchasePostDto {
    private final int productId;
    private final int amount;

    public PurchasePostDto(int userId, int productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public int getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }
}
