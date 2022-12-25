package com.alkafol.notificationmicroservice.models;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Not;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;
    @ElementCollection
    private List<String> usernames = new ArrayList<>();
    private String header;
    private String message;
    private LocalDateTime dateTime;

    public Notification(String header, String message, List<String> userIds) {
        this.header = header;
        this.usernames = userIds;
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }

    public List<String> getUserIds() {
        return usernames;
    }

    public void setUserIds(List<String> userIds) {
        this.usernames = userIds;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Notification(){

    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
