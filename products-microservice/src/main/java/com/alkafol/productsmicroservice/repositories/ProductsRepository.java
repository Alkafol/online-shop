package com.alkafol.productsmicroservice.repositories;

import com.alkafol.productsmicroservice.models.Product;
import com.alkafol.productsmicroservice.models.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    public List<Product> findAllByStatus(ProductStatus productStatus);
}
