package com.alkafol.notificationmicroservice.dto;

import com.alkafol.notificationmicroservice.models.Notification;

public class Mapper {
    public static Notification convertToNotification(NotificationPostDto notificationPostDto){
        return new Notification(
                notificationPostDto.getHeader(),
                notificationPostDto.getMessage(),
                notificationPostDto.getUsernames()
        );
    }

    public static NotificationGetDto convertToNotificationGetDto(Notification notification){
        return new NotificationGetDto(
                notification.getHeader(),
                notification.getMessage(),
                notification.getDateTime()
        );
    }
}
