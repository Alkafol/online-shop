package com.alkafol.productsmicroservice.dto;

import java.util.List;
import java.util.Map;

public class ProductGetDto {
    private final int productId;
    private final String name;
    private final String description;
    private final int organization;
    private final double price;
    private final int availableAmount;
    private final List<String> keyWords;
    private final Map<String, String> characteristics;
    private final Map<String, Double> assessments;

    public ProductGetDto(int productId, String name, String description, int organization, double price, int availableAmount, List<String> keyWords, Map<String, String> characteristics, Map<String, Double> assessments) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.organization = organization;
        this.price = price;
        this.availableAmount = availableAmount;
        this.keyWords = keyWords;
        this.characteristics = characteristics;
        this.assessments = assessments;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getOrganization() {
        return organization;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public Map<String, String> getCharacteristics() {
        return characteristics;
    }

    public Map<String, Double> getAssessments() {
        return assessments;
    }
}
