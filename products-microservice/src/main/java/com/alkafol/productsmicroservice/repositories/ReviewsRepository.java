package com.alkafol.productsmicroservice.repositories;

import com.alkafol.productsmicroservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Review, Integer> {
    public List<Review> findAllByUsername(String username);
}
