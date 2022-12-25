package com.alkafol.purchasesmicroservice.controllers;

import com.alkafol.purchasesmicroservice.dto.PurchaseGetDto;
import com.alkafol.purchasesmicroservice.dto.PurchasePostDto;
import com.alkafol.purchasesmicroservice.services.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {
    public PurchaseService purchaseService;

    public PurchasesController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/make")
    public void makePurchase(@RequestHeader("caller_username") String username,  @RequestBody PurchasePostDto purchasePostDto){
        purchaseService.makePurchase(username, purchasePostDto);
    }

    @GetMapping("/check/{username}/{productId}")
    public boolean checkUserPurchase(@PathVariable String username, @PathVariable int productId){
        return purchaseService.checkUserPurchase(username, productId);
    }

    @GetMapping("/view_my_history")
    public List<PurchaseGetDto> viewMyHistory(@RequestHeader("caller_username") String username){
        return purchaseService.viewUserHistory(username);
    }

    @GetMapping("/view_user_history/{username}")
    public List<PurchaseGetDto> viewUserHistory(@PathVariable String username){
        return purchaseService.viewUserHistory(username);
    }

    @PutMapping("/cancel_purchase/{purchaseId}")
    public void cancelPurchase(@RequestHeader("caller_username") String username, @PathVariable int purchaseId){
        purchaseService.cancelPurchase(username, purchaseId);
    }
}
