package com.alkafol.purchasesmicroservice.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;
    private String username;
    private int productId;
    private int amount;
    private LocalDateTime time;
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    public Purchase(int productId, int amount){
        this.productId = productId;
        this.amount = amount;
        this.time = LocalDateTime.now();
        this.status = PurchaseStatus.ACTIVE;
    }

    public Purchase() {

    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }
}
