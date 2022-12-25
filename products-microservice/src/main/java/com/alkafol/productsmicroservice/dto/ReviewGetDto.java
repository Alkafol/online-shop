package com.alkafol.productsmicroservice.dto;

public class ReviewGetDto {
    private final int id;
    private final String description;
    private final String username;
    private final double assessment;

    public ReviewGetDto(int id, String description, String username, double assessment) {
        this.id = id;
        this.description = description;
        this.username = username;
        this.assessment = assessment;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getUsername() {
        return username;
    }

    public double getAssessment() {
        return assessment;
    }
}
