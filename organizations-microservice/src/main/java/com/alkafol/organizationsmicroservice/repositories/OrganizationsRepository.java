package com.alkafol.organizationsmicroservice.repositories;

import com.alkafol.organizationsmicroservice.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationsRepository extends JpaRepository<Organization, Integer> {
}
