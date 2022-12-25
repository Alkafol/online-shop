package com.alkafol.notificationmicroservice.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class NotificationGetDto {
    private final String header;
    private final String message;
    private final LocalDateTime dateTime;

    public NotificationGetDto(String header, String message, LocalDateTime dateTime) {
        this.header = header;
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getHeader() {
        return header;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
