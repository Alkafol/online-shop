package com.alkafol.organizationsmicroservice.dto;

import com.alkafol.organizationsmicroservice.models.Organization;

import java.io.IOException;

public class Mapper {
    public static Organization mapToOrganization(String username, OrganizationPostDto organizationPostDto) throws IOException {
        return new Organization(
                username,
                organizationPostDto.getName(),
                organizationPostDto.getDescription(),
                organizationPostDto.getLogo()
        );
    }

    public static OrganizationGetDto mapToOrganizationGetDto(Organization organization) {
        return new OrganizationGetDto(
                organization.getOrganizationId(),
                organization.getUsername(),
                organization.getName(),
                organization.getDescription(),
                organization.getLogoContent(),
                organization.getProductsId(),
                organization.getOrganizationStatus()
        );
    }
}
