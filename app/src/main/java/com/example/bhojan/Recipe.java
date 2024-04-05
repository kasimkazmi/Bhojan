package com.example.bhojan;

public class Recipe {
    private String dishName;
    private String mealType;

    public Recipe(String dishName, String mealType) {
        this.dishName = dishName;
        this.mealType = mealType;
    }

    public String getDishName() {
        return dishName;
    }

    public String getMealType() {
        return mealType;
    }
}