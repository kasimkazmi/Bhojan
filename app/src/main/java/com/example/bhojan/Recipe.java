package com.example.bhojan;

import android.graphics.Bitmap;

public class Recipe {
    private String dishName;
    private String mealType;
    private String ingredients;
    private String description;
    private String imageUrl;

    public Recipe() {
        // Default constructor required for Firestore
    }

    public Recipe(String dishName, String mealType, String ingredients, String description) {
        this.dishName = dishName;
        this.mealType = mealType;
        this.ingredients = ingredients;
        this.description = description;
    }

    public Recipe(String dishName, String mealType, String ingredients, String description, Bitmap image) {
    }

    public String getDishName() {
        return dishName;
    }

    public String getMealType() {
        return mealType;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}