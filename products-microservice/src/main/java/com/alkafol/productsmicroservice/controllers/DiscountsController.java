package com.alkafol.productsmicroservice.controllers;

import com.alkafol.productsmicroservice.dto.DiscountGetDto;
import com.alkafol.productsmicroservice.dto.DiscountPostDto;
import com.alkafol.productsmicroservice.services.DiscountsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountsController {
    public DiscountsService discountsService;

    public DiscountsController(DiscountsService discountsService) {
        this.discountsService = discountsService;
    }

    @PostMapping("/add")
    public void addDiscount(@RequestBody DiscountPostDto discountPostDto){
        discountsService.addDiscount(discountPostDto);
    }

    @PutMapping("/add_products/{discountId}/{productIds}")
    public void addProducts(@PathVariable int discountId, @PathVariable List<Integer> productIds){
        discountsService.addProductsToDiscount(discountId, productIds);
    }

    @PutMapping("/remove_products/{discountId}/{productsIds}")
    public void removeProducts(@PathVariable int discountId, @PathVariable List<Integer> productsIds){
        discountsService.removeProductsFromDiscount(discountId, productsIds);
    }

    @PutMapping("/change_amount/{discountId}/{amount}")
    public void changeAmount(@PathVariable int discountId, @PathVariable int amount){
        discountsService.changeDiscountAmount(discountId, amount);
    }

    @PutMapping("/change_expiry/{discountId}/{date}")
    public void changeExpiryDate(@PathVariable int discountId, @PathVariable LocalDate date){
        discountsService.changeExpiryDate(discountId, date);
    }

    @GetMapping("/get_all")
    public List<DiscountGetDto> getAllDiscounts(){
        return discountsService.getAllDiscounts();
    }
}
