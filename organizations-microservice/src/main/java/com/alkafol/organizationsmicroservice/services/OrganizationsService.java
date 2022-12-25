package com.alkafol.organizationsmicroservice.services;

import com.alkafol.organizationsmicroservice.dto.Mapper;
import com.alkafol.organizationsmicroservice.dto.OrganizationGetDto;
import com.alkafol.organizationsmicroservice.dto.OrganizationPostDto;
import com.alkafol.organizationsmicroservice.models.Organization;
import com.alkafol.organizationsmicroservice.models.OrganizationStatus;
import com.alkafol.organizationsmicroservice.repositories.OrganizationsRepository;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrganizationsService {
    public OrganizationsRepository organizationsRepository;
    public RestTemplate restTemplate;
    public static final double COMMISSION = 0.05;

    public OrganizationsService(OrganizationsRepository organizationsRepository, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.organizationsRepository = organizationsRepository;
    }

    public void addProduct(int organizationId, String username, int productId){
        Organization organization = organizationsRepository.findById(organizationId).orElseThrow(NoSuchElementException::new);
        if (organization.getOrganizationStatus() != OrganizationStatus.ACTIVE || !organization.getUsername().equals(username)){
            throw new RuntimeException("Can't add product due to organization's issues");
        }

        organization.getProductsId().add(productId);
        organizationsRepository.save(organization);
    }

    public void createOrganization(String username, OrganizationPostDto organizationPostDto) throws IOException {
        Organization organization = Mapper.mapToOrganization(username, organizationPostDto);
        organizationsRepository.save(organization);
    }

    public void changeStatus(int organizationId, OrganizationStatus organizationStatus){
        Organization organization = organizationsRepository.findById(organizationId).orElseThrow(NoSuchElementException::new);
        if (organizationStatus == OrganizationStatus.BANNED || organizationStatus == OrganizationStatus.FREEZE){
            for (Integer productId : organization.getProductsId()) {
                restTemplate.put("http://localhost:8081/products/change_status/" + productId + "/" + organizationStatus.name(), RequestEntity.EMPTY);
            }
        }

        organization.setOrganizationStatus(organizationStatus);
        organizationsRepository.save(organization);
    }

    public void getMoneyFromPurchase(int organizationId, double amount){
        Organization organization = organizationsRepository.findById(organizationId).orElseThrow(NoSuchElementException::new);
        amount = amount * (1 - COMMISSION);

        restTemplate.put("http://localhost:8085/users/add_money/" + organization.getUsername() + "/" + amount, RequestEntity.EMPTY);
    }

    public void takeMoney(int organizationId, double amount) {
        Organization organization = organizationsRepository.findById(organizationId).orElseThrow(NoSuchElementException::new);
        amount = amount * (1 - COMMISSION);

        restTemplate.put("http://localhost:8085/users/take_money/" + organization.getUsername() + "/" + amount, RequestEntity.EMPTY);
    }

    public List<OrganizationGetDto> getAllOrganizations() {
        return organizationsRepository.findAll().stream().map(Mapper::mapToOrganizationGetDto).toList();
    }
}
