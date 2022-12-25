package com.alkafol.productsmicroservice.services;

import com.alkafol.productsmicroservice.dto.DiscountGetDto;
import com.alkafol.productsmicroservice.dto.DiscountPostDto;
import com.alkafol.productsmicroservice.dto.Mapper;
import com.alkafol.productsmicroservice.models.Discount;
import com.alkafol.productsmicroservice.repositories.DiscountsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DiscountsService {
    public DiscountsRepository discountsRepository;
    public ProductsService productsService;

    public DiscountsService(DiscountsRepository discountsRepository, ProductsService productsService) {
        this.discountsRepository = discountsRepository;
        this.productsService = productsService;
    }

    public void addDiscount(DiscountPostDto discountPostDto) {
        Discount discount = Mapper.mapToDiscount(discountPostDto);
        discountsRepository.save(discount);
    }

    public void deleteDiscount(int discountId){
        discountsRepository.deleteById(discountId);
    }

    public void addProductsToDiscount(int discountId, List<Integer> productIds) {
        Discount discount = discountsRepository.findById(discountId).orElseThrow(NoSuchElementException::new);
        discount.getProducts().addAll(productIds.stream()
                .map(product -> productsService.findById(product))
                .toList()
        );
        discountsRepository.save(discount);
    }

    public void removeProductsFromDiscount(int discountId, List<Integer> productsIds){
        Discount discount = discountsRepository.findById(discountId).orElseThrow(NoSuchElementException::new);
        discount.getProducts().removeIf(product -> productsIds.contains(product.getProductId()));
        discountsRepository.save(discount);
    }

    public void changeDiscountAmount(int discountId, double amount){
        Discount discount = discountsRepository.findById(discountId).orElseThrow(NoSuchElementException::new);
        discount.setAmount(amount);
        discountsRepository.save(discount);
    }

    public void changeExpiryDate(int discountId, LocalDate localDate){
        Discount discount = discountsRepository.findById(discountId).orElseThrow(NoSuchElementException::new);
        discount.setExpiryDate(localDate);
        discountsRepository.save(discount);
    }


    public List<DiscountGetDto> getAllDiscounts() {
        return discountsRepository.findAll().stream().map(Mapper::mapToDiscountGetDto).toList();
    }
}
