package com.alkafol.productsmicroservice.services;

import com.alkafol.productsmicroservice.dto.Mapper;
import com.alkafol.productsmicroservice.dto.ReviewPostDto;
import com.alkafol.productsmicroservice.models.Review;
import com.alkafol.productsmicroservice.repositories.ReviewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReviewsService {
    public ReviewsRepository reviewsRepository;
    public ProductsService productsService;
    public RestTemplate restTemplate;

    public ReviewsService(ReviewsRepository reviewsRepository, ProductsService productsService, RestTemplate restTemplate) {
        this.reviewsRepository = reviewsRepository;
        this.productsService = productsService;
        this.restTemplate = restTemplate;
    }

    public void addReview(String username, ReviewPostDto reviewPostDto){
        Boolean res = restTemplate.getForObject("http://localhost:8082/purchases/check/" + username + "/" + reviewPostDto.getProductId(), Boolean.class);
        if (Boolean.FALSE.equals(res)){
            throw new RuntimeException("Can't add review before buying");
        }
        if (reviewsRepository.findAllByUsername(username).stream()
                .anyMatch((review) -> review.getProduct().getProductId() == reviewPostDto.getProductId())){
            throw new RuntimeException("You've already posted review");
        }

        Review review = Mapper.mapToReview(reviewPostDto);
        review.setUsername(username);
        review.setProduct(productsService.findById(reviewPostDto.getProductId()));
        reviewsRepository.save(review);
        productsService.addAssessmentWithoutChecking(reviewPostDto.getProductId(), username, reviewPostDto.getAssessment());
    }

    public void changeReviewDescription(int reviewId, String description){
        Review review = reviewsRepository.findById(reviewId).orElseThrow(NoSuchElementException::new);
        review.setDescription(description);
        reviewsRepository.save(review);
    }

    public void changeReviewAssessment(int reviewId, double assessment){
        Review review = reviewsRepository.findById(reviewId).orElseThrow(NoSuchElementException::new);
        review.setAssessment(assessment);
        productsService.addAssessmentWithoutChecking(review.getProduct().getProductId(), review.getUsername() ,assessment);
        reviewsRepository.save(review);
    }
}
