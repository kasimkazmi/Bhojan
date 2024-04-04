package com.example.bhojan;
public class Recipe {
    private int imageResourceId;
    private String title;

    public Recipe(int imageResourceId, String title) {
        this.imageResourceId = imageResourceId;
        this.title = title;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getTitle() {
        return title;
    }
}