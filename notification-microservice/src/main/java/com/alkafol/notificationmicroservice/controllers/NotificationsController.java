package com.alkafol.notificationmicroservice.controllers;

import com.alkafol.notificationmicroservice.dto.NotificationGetDto;
import com.alkafol.notificationmicroservice.dto.NotificationPostDto;
import com.alkafol.notificationmicroservice.services.NotificationsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {
    public NotificationsService notificationsService;

    public NotificationsController(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    @PostMapping("/send")
    public void sendNotification(@RequestBody NotificationPostDto notificationPostDto){
        notificationsService.sendNotification(notificationPostDto);
    }

    @GetMapping("/check")
    public List<NotificationGetDto> checkNotification(@RequestHeader("caller_username") String username){
        return notificationsService.checkNotifications(username);
    }
}
