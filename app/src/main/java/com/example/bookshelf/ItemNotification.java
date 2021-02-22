package com.example.bookshelf;

import org.json.JSONArray;

public class ItemNotification {
    private int avatar;
    private int status;
    private String title;
    private String description;

    public ItemNotification() {
    }

    public ItemNotification(int avatar, int status, String title, String description) {
        this.avatar = avatar;
        this.status = status;
        this.title = title;
        this.description = description;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
