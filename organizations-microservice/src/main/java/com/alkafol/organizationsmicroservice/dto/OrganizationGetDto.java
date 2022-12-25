package com.alkafol.organizationsmicroservice.dto;

import com.alkafol.organizationsmicroservice.models.OrganizationStatus;

import java.util.List;

public class OrganizationGetDto {
    private final int organizationId;
    private final String username;
    private final String name;
    private final String description;
    private final byte[] logo;
    private final List<Integer> productIds;
    private final OrganizationStatus status;

    public OrganizationGetDto(int organizationId, String username, String name, String description, byte[] logo, List<Integer> productIds, OrganizationStatus status) {
        this.organizationId = organizationId;
        this.username = username;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.productIds = productIds;
        this.status = status;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public OrganizationStatus getStatus() {
        return status;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public String getUsername() {
        return username;
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
