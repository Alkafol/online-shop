package com.alkafol.purchasesmicroservice.dto;

import com.alkafol.purchasesmicroservice.models.Purchase;

public class Mapper {
    public static Purchase convertToPurchase(PurchasePostDto purchasePostDto) {
        return new Purchase(
                purchasePostDto.getProductId(),
                purchasePostDto.getAmount()
        );
    }

    public static PurchaseGetDto convertToPurchaseGetDto(Purchase purchase) {
        return new PurchaseGetDto(
                purchase.getPurchaseId(),
                purchase.getUsername(),
                purchase.getProductId(),
                purchase.getAmount(),
                purchase.getTime(),
                purchase.getTotalPrice(),
                purchase.getStatus()
        );
    }
}
