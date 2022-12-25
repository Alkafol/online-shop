package com.alkafol.purchasesmicroservice.services;

import com.alkafol.purchasesmicroservice.dto.Mapper;
import com.alkafol.purchasesmicroservice.dto.PurchaseGetDto;
import com.alkafol.purchasesmicroservice.dto.PurchasePostDto;
import com.alkafol.purchasesmicroservice.models.Purchase;
import com.alkafol.purchasesmicroservice.models.PurchaseStatus;
import com.alkafol.purchasesmicroservice.repositories.PurchasesRepository;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PurchaseService {
    private PurchasesRepository purchasesRepository;
    private RestTemplate restTemplate;

    public PurchaseService(PurchasesRepository purchasesRepository, RestTemplate restTemplate) {
        this.purchasesRepository = purchasesRepository;
        this.restTemplate = restTemplate;
    }

    public void makePurchase(String username, PurchasePostDto purchasePostDto){
        Purchase purchase = Mapper.convertToPurchase(purchasePostDto);
        purchase.setUsername(username);

        Double totalPrice = restTemplate.getForObject(
                "http://localhost:8081/products/count_price/" + purchase.getProductId() + "/" + purchase.getAmount(),
                Double.class
        );

        restTemplate.put("http://localhost:8085/users/take_money/" + username + "/" + totalPrice, RequestEntity.EMPTY);

        try{
            restTemplate.put("http://localhost:8081/products/buy_products/" + purchase.getProductId() + "/" + purchase.getAmount() + "/" + totalPrice, RequestEntity.EMPTY);
        }catch (Exception e){
            restTemplate.put("http://localhost:8085/users/add_money/" + username + "/" + totalPrice, RequestEntity.EMPTY);
            throw new RuntimeException();
        }

        purchase.setTotalPrice(totalPrice);
        purchasesRepository.save(purchase);
    }

    public void cancelPurchase(String username, int purchaseId){
        Purchase purchase = purchasesRepository.findById(purchaseId).orElseThrow(NoSuchElementException::new);
        if (!purchase.getUsername().equals(username) || purchase.getStatus() == PurchaseStatus.CANCELLED){
            throw new RuntimeException("Can't cancel this purchase");
        }

        if (LocalDateTime.now().isAfter(ChronoUnit.MINUTES.addTo(purchase.getTime(), 60 * 24))){
            throw new RuntimeException("Purchase is too old");
        }

        restTemplate.put("http://localhost:8085/users/add_money/" + username + "/" + purchase.getTotalPrice(), RequestEntity.EMPTY);
        restTemplate.put("http://localhost:8081/products/return_products/" + purchase.getProductId() + "/" + purchase.getAmount() + "/" + purchase.getTotalPrice(), RequestEntity.EMPTY);
        purchase.setStatus(PurchaseStatus.CANCELLED);
        purchasesRepository.save(purchase);
    }

    public List<PurchaseGetDto> viewUserHistory(String username){
        return purchasesRepository.findByUsername(username).stream().map(Mapper::convertToPurchaseGetDto).toList();
    }

    public boolean checkUserPurchase(String username, int productId) {
        List<Purchase> userPurchases = purchasesRepository.findByUsername(username);
        for (Purchase purchase : userPurchases){
            if (purchase.getProductId() == productId){
                return true;
            }
        }

        return false;
    }
}
