package com.alkafol.organizationsmicroservice.controllers;

import com.alkafol.organizationsmicroservice.dto.OrganizationGetDto;
import com.alkafol.organizationsmicroservice.dto.OrganizationPostDto;
import com.alkafol.organizationsmicroservice.models.OrganizationStatus;
import com.alkafol.organizationsmicroservice.services.OrganizationsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {
    public OrganizationsService organizationsService;

    public OrganizationController(OrganizationsService organizationsService) {
        this.organizationsService = organizationsService;
    }

    @PostMapping("/create")
    public void createOrganization(@RequestHeader("caller_username") String username,  @RequestBody OrganizationPostDto organizationPostDto) throws IOException {
        organizationsService.createOrganization(username, organizationPostDto);
    }

    @PutMapping("/change_status/{organizationId}/{organizationStatus}")
    public void changeStatus(@PathVariable int organizationId, @PathVariable OrganizationStatus organizationStatus){
        organizationsService.changeStatus(organizationId, organizationStatus);
    }

    @PutMapping("/add_product/{organizationId}/{username}/{productId}")
    public void addProduct(@PathVariable int organizationId, @PathVariable String username, @PathVariable int productId){
        organizationsService.addProduct(organizationId, username, productId);
    }

    @PutMapping("/add_money/{organizationId}/{amount}")
    public void addMoney(@PathVariable int organizationId, @PathVariable double amount){
        organizationsService.getMoneyFromPurchase(organizationId, amount);
    }

    @PutMapping("/take_money/{organizationId}/{amount}")
    public void takeMoney(@PathVariable int organizationId, @PathVariable double amount){
        organizationsService.takeMoney(organizationId, amount);
    }

    @GetMapping("/get_all")
    public List<OrganizationGetDto> getAllOrganizations(){
        return organizationsService.getAllOrganizations();
    }
}
