package com.alkafol.productsmicroservice.services;

import com.alkafol.productsmicroservice.dto.Mapper;
import com.alkafol.productsmicroservice.dto.ProductGetDto;
import com.alkafol.productsmicroservice.dto.ProductPostDto;
import com.alkafol.productsmicroservice.dto.ReviewGetDto;
import com.alkafol.productsmicroservice.models.Discount;
import com.alkafol.productsmicroservice.models.Product;
import com.alkafol.productsmicroservice.models.Review;
import com.alkafol.productsmicroservice.models.ProductStatus;
import com.alkafol.productsmicroservice.repositories.ProductsRepository;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;
    private RestTemplate restTemplate;

    public ProductsService(ProductsRepository productsRepository, RestTemplate restTemplate){
        this.productsRepository = productsRepository;
        this.restTemplate = restTemplate;
    }

    public void addProduct(String username, ProductPostDto productDto){
        Product product = Mapper.mapToProduct(productDto);

        productsRepository.save(product);

        try{
            restTemplate.put("http://localhost:8083/organizations/add_product/" + product.getOrganizationId() + "/"  + username + "/" + product.getProductId(), RequestEntity.EMPTY);
        }
        catch (Exception e){
            productsRepository.delete(product);
            throw new RuntimeException("Invalid organization");
        }
    }

    public void addReview(int productId, Review review){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        product.getReviews().add(review);
        product.getAssessments().put(review.getUsername(), review.getAssessment());
        productsRepository.save(product);
    }

    public void changeProductName(int productId, String name){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        product.setName(name);
        productsRepository.save(product);
    }

    public void changeProductDescription(int productId, String description){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        product.setDescription(description);
        productsRepository.save(product);
    }

    public void changeOrganization(int productId, int organizationId){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        product.setOrganizationId(organizationId);
        productsRepository.save(product);
    }

    public void changePrice(int productId, double price){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        product.setPrice(price);
        productsRepository.save(product);
    }

    public void changeAvailableAmount(int productId, int amount){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        product.setAvailableAmount(amount);
        productsRepository.save(product);
    }

    public void addKeyWords(int productId, List<String> keyWords){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        product.getKeyWords().addAll(keyWords);
        productsRepository.save(product);
    }

    public void removeKeyWords(int productId, List<String> keyWords){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        product.getKeyWords().removeAll(keyWords);
        productsRepository.save(product);
    }

    public void addCharacteristics(int productId, List<String> characteristics){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        Map<String, String> map = new HashMap<>(){{
            for (int i = 0; i < characteristics.size() - 1; i += 2){
                put(characteristics.get(i), characteristics.get(i + 1));
            }
        }};

        product.getCharacteristics().putAll(map);
        productsRepository.save(product);
    }

    public void removeCharacteristics(int productId, List<String> characteristics){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        for (String characteristic : characteristics) {
            product.getCharacteristics().remove(characteristic);
        }
        productsRepository.save(product);
    }

    public void changeStatus(int productId, ProductStatus productStatus){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        product.setStatus(productStatus);
        productsRepository.save(product);
    }

    public void addAssessment(int productId, String username, double assessment){
        Boolean res = restTemplate.getForObject("http://localhost:8082/purchases/check/" + username + "/" + productId, Boolean.class);
        if (Boolean.FALSE.equals(res)){
            throw new RuntimeException("Can't add assessment before buying");
        }

        addAssessmentWithoutChecking(productId, username, assessment);
    }

    public void addAssessmentWithoutChecking(int productId, String username, double assessment){
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        product.getAssessments().put(username, assessment);
        productsRepository.save(product);
    }

    public Product findById(int productId){
        return productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
    }

    public double countPrice(int productId, int amount) {
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        if (product.getAvailableAmount() < amount){
            throw new RuntimeException("Not enough money");
        }
        List<Discount> validDiscounts = product.getDiscounts()
                .stream()
                .filter(discount -> discount.getExpiryDate().isAfter(LocalDate.now()))
                .sorted((a, b) -> {return Double.compare(a.getAmount(), b.getAmount());})
                .toList();
        double price = product.getPrice();
        for (Discount discount : validDiscounts){
            price -= price * discount.getAmount() * 0.01;
        }

        return price * amount;
    }

    public void buyProducts(int productId, int amount, double price) {
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        if (product.getAvailableAmount() < amount || product.getStatus() != ProductStatus.ACTIVE){
            throw new RuntimeException("Can't by this product");
        }

        restTemplate.put("http://localhost:8083/organizations/add_money/" + product.getOrganizationId() + "/" + price, RequestEntity.EMPTY);

        product.setAvailableAmount(product.getAvailableAmount() - amount);
        productsRepository.save(product);
    }

    public void returnProducts(int productId, int amount, double price) {
        Product product = productsRepository.findById(productId).orElseThrow(NoSuchElementException::new);

        restTemplate.put("http://localhost:8083/organizations/take_money/" + product.getOrganizationId() + "/" + price, RequestEntity.EMPTY);

        product.setAvailableAmount(product.getAvailableAmount() + amount);
        productsRepository.save(product);
    }

    public List<ProductGetDto> getAllProducts() {
        return productsRepository.findAllByStatus(ProductStatus.ACTIVE).stream().map(Mapper::mapToProductGetDto).toList();
    }

    public List<ProductGetDto> getAllProductsByStatus(ProductStatus productStatus){
        return productsRepository.findAllByStatus(productStatus).stream().map(Mapper::mapToProductGetDto).toList();
    }

    public List<ReviewGetDto> getProductReviews(int productId){
        return productsRepository.findById(productId).orElseThrow(NoSuchElementException::new).getReviews().stream()
                .map(Mapper::mapToReviewGetDto)
                .toList();
    }
}
