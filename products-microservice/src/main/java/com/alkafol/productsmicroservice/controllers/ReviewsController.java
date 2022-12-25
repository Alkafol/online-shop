package com.alkafol.productsmicroservice.controllers;

import com.alkafol.productsmicroservice.dto.ReviewPostDto;
import com.alkafol.productsmicroservice.services.ReviewsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    public ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @PostMapping("/add")
    public void addReview(@RequestHeader("caller_username") String username, @RequestBody ReviewPostDto reviewDto){
        reviewsService.addReview(username, reviewDto);
    }

    @PutMapping("/change_description/{reviewId}/{description}")
    public void changeReviewDescription(@PathVariable int reviewId, @PathVariable String description){
        reviewsService.changeReviewDescription(reviewId, description);
    }

    @PutMapping("/change_assessment/{reviewId}/{assessment}")
    public void changeReviewAssessment(@PathVariable int reviewId, @PathVariable double assessment){
        reviewsService.changeReviewAssessment(reviewId, assessment);
    }
}
