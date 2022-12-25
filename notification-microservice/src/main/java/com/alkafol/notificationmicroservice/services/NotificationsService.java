package com.alkafol.notificationmicroservice.services;

import com.alkafol.notificationmicroservice.dto.Mapper;
import com.alkafol.notificationmicroservice.dto.NotificationGetDto;
import com.alkafol.notificationmicroservice.dto.NotificationPostDto;
import com.alkafol.notificationmicroservice.models.Notification;
import com.alkafol.notificationmicroservice.repositories.NotificationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsService {
    private NotificationsRepository notificationsRepository;

    public NotificationsService(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    public void sendNotification(NotificationPostDto notificationPostDto){
        Notification notification = Mapper.convertToNotification(notificationPostDto);
        notificationsRepository.save(notification);
    }

    public List<NotificationGetDto> checkNotifications(String username){
        return notificationsRepository.findAllByUsernamesContains(username).stream()
                .map(Mapper::convertToNotificationGetDto)
                .toList();
    }
}
