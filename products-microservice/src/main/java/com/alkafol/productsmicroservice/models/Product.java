package com.alkafol.productsmicroservice.models;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String name;
    private String description;
    private int organizationId;
    private double price;
    private int availableAmount;

    @ManyToMany(mappedBy = "products")
    private List<Discount> discounts;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @ElementCollection
    private List<String> keyWords;

    @Type(JsonType.class)
    @Column(columnDefinition = "text")
    private Map<String, String> characteristics;

    @ElementCollection
    @CollectionTable(name = "product_assessments",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "productId")
            })
    @MapKeyColumn(name = "username")
    @Column(name = "assessment")
    private Map<String, Double> assessments;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    public Product() {
    }

    public Product(String name, String description, int organizationId, double price, int amount, List<String> keyWords, Map<String, String> characteristics) {
        this.name = name;
        this.description = description;
        this.organizationId = organizationId;
        this.price = price;
        this.availableAmount = amount;
        this.keyWords = keyWords;
        this.characteristics = characteristics;
        this.status = ProductStatus.WAITING_APPROVAL;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public Map<String, String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Map<String, String> characteristics) {
        this.characteristics = characteristics;
    }

    public Map<String, Double> getAssessments() {
        return assessments;
    }

    public void setAssessments(Map<String, Double> assessments) {
        this.assessments = assessments;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
