package com.alkafol.productsmicroservice.repositories;

import com.alkafol.productsmicroservice.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountsRepository extends JpaRepository<Discount, Integer> {
}
