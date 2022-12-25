package com.alkafol.productsmicroservice.dto;

import java.util.HashMap;
import java.util.List;

public class ProductPostDto {
    private final String name;
    private final String description;
    private final int organizationId;
    private final double price;
    private final int amount;
    private final List<String> keyWords;
    private final HashMap<String, String> characteristics;

    public ProductPostDto(String name, String description, int organizationId, double price, int amount, List<String> keyWords, HashMap<String, String> characteristics) {
        this.name = name;
        this.description = description;
        this.organizationId = organizationId;
        this.price = price;
        this.amount = amount;
        this.keyWords = keyWords;
        this.characteristics = characteristics;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public HashMap<String, String> getCharacteristics() {
        return characteristics;
    }
}
