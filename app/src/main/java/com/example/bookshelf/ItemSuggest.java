package com.example.bookshelf;

import org.json.JSONArray;

public class ItemSuggest {
    private String description;
    private int imgId;
    private JSONArray listItem;

    public ItemSuggest() {
    }

    public ItemSuggest(String description, int imgId) {
        this.description = description;
        this.imgId = imgId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public JSONArray getListItem() {
        return listItem;
    }

    public void setListItem(JSONArray listItem) {
        this.listItem = listItem;
    }
}
