package com.alkafol.purchasesmicroservice.repositories;

import com.alkafol.purchasesmicroservice.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchase, Integer> {
    public List<Purchase> findByUsername(String username);
}
