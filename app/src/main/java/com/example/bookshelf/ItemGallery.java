package com.example.bookshelf;

import org.json.JSONArray;

public class ItemGallery {
    private String title;
    private int avatar;
    private int[] listImg;

    public ItemGallery() {
    }

    public ItemGallery(String title, int avatar) {
        this.title = title;
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int[] getListImg() {
        return listImg;
    }

    public void setListImg(int[] listImg) {
        this.listImg = listImg;
    }
}
