package com.example.bookshelf;

import org.json.JSONArray;

public class ItemImage {
    private int src;

    public ItemImage() {
    }

    public ItemImage(int src) {
        this.src = src;
    }
    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }
}
