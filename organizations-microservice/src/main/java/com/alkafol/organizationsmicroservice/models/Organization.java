package com.alkafol.organizationsmicroservice.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int organizationId;
    private String username;
    private String name;
    private String description;

    @Lob
    byte[] logoContent;

    @ElementCollection
    private List<Integer> productsId = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrganizationStatus organizationStatus;

    public Organization(String username, String name, String description, byte[] logoContent){
        this.username = username;
        this.name = name;
        this.description = description;
        this.logoContent = logoContent;
        this.organizationStatus = OrganizationStatus.WAITING_APPROVAL;
    }

    public Organization() {

    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Integer> productsId) {
        this.productsId = productsId;
    }

    public OrganizationStatus getOrganizationStatus() {
        return organizationStatus;
    }

    public void setOrganizationStatus(OrganizationStatus organizationStatus) {
        this.organizationStatus = organizationStatus;
    }

    public byte[] getLogoContent() {
        return logoContent;
    }

    public void setLogoContent(byte[] logoContent) {
        this.logoContent = logoContent;
    }
}
