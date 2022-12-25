package com.alkafol.organizationsmicroservice.dto;

public class OrganizationPostDto {
    private final String name;
    private final String description;
    private final byte[] logo;

    public OrganizationPostDto(String name, String description, byte[] logo) {
        this.name = name;
        this.description = description;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getLogo() {
        return logo;
    }
}
