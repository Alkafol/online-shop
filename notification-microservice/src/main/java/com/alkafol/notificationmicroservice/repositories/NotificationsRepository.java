package com.alkafol.notificationmicroservice.repositories;

import com.alkafol.notificationmicroservice.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationsRepository extends JpaRepository<Notification, Integer> {
    public List<Notification> findAllByUsernamesContains(String username);
}
