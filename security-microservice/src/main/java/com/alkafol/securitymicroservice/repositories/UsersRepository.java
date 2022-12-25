package com.alkafol.securitymicroservice.repositories;

import com.alkafol.securitymicroservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
