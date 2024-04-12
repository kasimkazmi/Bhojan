package com.example.bhojan;

import android.graphics.Bitmap;

public class Recipe {
    private String dishName;
    private String mealType;
    private String ingredients;
    private String description;
    private Bitmap image;

    public Recipe(String dishName, String mealType, String ingredients, String description, Bitmap image) {
        this.dishName = dishName;
        this.mealType = mealType;
        this.ingredients = ingredients;
        this.description = description;
        this.image = image;
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

    public Bitmap getImage() {
        return image;
    }
}