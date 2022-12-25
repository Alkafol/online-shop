package com.alkafol.productsmicroservice.dto;

public class ReviewPostDto {
    private final int productId;
    private final String description;
    private final double assessment;

    public ReviewPostDto(String description, double assessment, int productId) {
        this.productId = productId;
        this.description = description;
        this.assessment = assessment;
    }

    public String getDescription() {
        return description;
    }

    public double getAssessment() {
        return assessment;
    }

    public int getProductId() {
        return productId;
    }
}
