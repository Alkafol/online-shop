package com.alkafol.notificationmicroservice.dto;

import java.util.List;

public class NotificationPostDto {
    private final String header;
    private final String message;
    private final List<String> usernames;

    public NotificationPostDto(String header, String message, List<String> usernames) {
        this.header = header;
        this.message = message;
        this.usernames = usernames;
    }

    public String getHeader() {
        return header;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getUsernames() {
        return usernames;
    }
}
