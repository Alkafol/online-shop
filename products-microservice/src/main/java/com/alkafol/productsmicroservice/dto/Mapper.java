package com.alkafol.productsmicroservice.dto;

import com.alkafol.productsmicroservice.models.Discount;
import com.alkafol.productsmicroservice.models.Product;
import com.alkafol.productsmicroservice.models.Review;

import java.time.LocalDate;

public class Mapper {

    public static Product mapToProduct(ProductPostDto productDto){
        return new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getOrganizationId(),
                productDto.getPrice(),
                productDto.getAmount(),
                productDto.getKeyWords(),
                productDto.getCharacteristics()
        );
    }

    public static Discount mapToDiscount(DiscountPostDto discountDto){
        return new Discount(
                discountDto.getExpiryDate(),
                discountDto.getAmount()
        );
    }

    public static Review mapToReview(ReviewPostDto reviewDto){
        return new Review(
                reviewDto.getDescription(),
                reviewDto.getAssessment()
        );
    }

    public static ProductGetDto mapToProductGetDto(Product product){
        return new ProductGetDto(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getOrganizationId(),
                product.getPrice(),
                product.getAvailableAmount(),
                product.getKeyWords(),
                product.getCharacteristics(),
                product.getAssessments()
        );
    }

    public static DiscountGetDto mapToDiscountGetDto(Discount discount){
        return new DiscountGetDto(
            discount.getDiscountId(),
            discount.getProducts().stream().map(Product::getProductId).toList(),
            discount.getExpiryDate(),
            discount.getAmount()
        );
    }

    public static ReviewGetDto mapToReviewGetDto(Review review){
        return new ReviewGetDto(
                review.getReviewId(),
                review.getDescription(),
                review.getUsername(),
                review.getAssessment()
        );
    }

}
