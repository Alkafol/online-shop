package com.alkafol.productsmicroservice.controllers;

import com.alkafol.productsmicroservice.dto.ProductGetDto;
import com.alkafol.productsmicroservice.dto.ProductPostDto;
import com.alkafol.productsmicroservice.dto.ReviewGetDto;
import com.alkafol.productsmicroservice.models.Product;
import com.alkafol.productsmicroservice.models.ProductStatus;
import com.alkafol.productsmicroservice.services.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    public ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping("/add")
    public void addProduct(@RequestHeader("caller_username") String username, @RequestBody ProductPostDto productDto) {
        productsService.addProduct(username, productDto);
    }

    @PutMapping("/change_name/{productId}/{name}")
    public void changeProductName(@PathVariable int productId, @PathVariable String name) {
        productsService.changeProductName(productId, name);
    }

    @PutMapping("/change_description/{productId}/{description}")
    public void changeProductDescription(@PathVariable int productId, @PathVariable String description) {
        productsService.changeProductDescription(productId, description);
    }

    @PutMapping("/change_organization/{productId}/{organizationId}")
    public void changeOrganization(@PathVariable int productId, @PathVariable int organizationId) {
        productsService.changeOrganization(productId, organizationId);
    }

    @PutMapping("/change_price/{productId}/{price}")
    public void changePrice(@PathVariable int productId, @PathVariable double price) {
        productsService.changePrice(productId, price);
    }

    @PutMapping("/change_amount/{productId}/{amount}")
    public void changeAmount(@PathVariable int productId, @PathVariable int amount) {
        productsService.changeAvailableAmount(productId, amount);
    }

    @PutMapping("/add_keywords/{productId}/{keyWords}")
    public void addKeyWords(@PathVariable int productId, @PathVariable List<String> keyWords) {
        productsService.addKeyWords(productId, keyWords);
    }

    @PutMapping("/remove_keywords/{productId}/{keyWords}")
    public void removeKeyWords(@PathVariable int productId, @PathVariable List<String> keyWords) {
        productsService.removeKeyWords(productId, keyWords);
    }

    @PutMapping("/remove_characteristics/{productId}/{characteristics}")
    public void removeCharacteristics(@PathVariable int productId, @PathVariable List<String> characteristics) {
        productsService.removeCharacteristics(productId, characteristics);
    }

    @PutMapping("/add_characteristics/{productId}/{characteristics}")
    public void addCharacteristics(@PathVariable int productId, @PathVariable List<String> characteristics) {
        productsService.addCharacteristics(productId, characteristics);
    }

    @PutMapping("/change_status/{productId}/{productStatus}")
    public void changeStatus(@PathVariable int productId, @PathVariable ProductStatus productStatus) {
        productsService.changeStatus(productId, productStatus);
    }

    @GetMapping("/count_price/{productId}/{amount}")
    public double countPrice(@PathVariable int productId, @PathVariable int amount) {
        return productsService.countPrice(productId, amount);
    }

    @PutMapping("/buy_products/{productId}/{amount}/{price}")
    public void buyProducts(@PathVariable int productId, @PathVariable int amount, @PathVariable double price) {
        productsService.buyProducts(productId, amount, price);
    }

    @PutMapping("/return_products/{productId}/{amount}/{price}")
    public void returnProducts(@PathVariable int productId, @PathVariable int amount, @PathVariable double price) {
        productsService.returnProducts(productId, amount, price);
    }

    @PutMapping("/add_assessment/{productId}/{assessment}")
    public void addAssessment(@RequestHeader("caller_username") String username, @PathVariable int productId, @PathVariable double assessment){
        productsService.addAssessment(productId, username, assessment);
    }

    @GetMapping("/get_all")
    public List<ProductGetDto> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("/get_by_status/{productStatus}")
    public List<ProductGetDto> getAllByStatus(@PathVariable ProductStatus productStatus){
        return productsService.getAllProductsByStatus(productStatus);
    }

    @GetMapping("/get_product_reviews/{productId}")
    public List<ReviewGetDto> getProductReviews(@PathVariable int productId){
        return productsService.getProductReviews(productId);
    }
}
