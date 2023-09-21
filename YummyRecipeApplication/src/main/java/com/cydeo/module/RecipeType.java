package com.cydeo.module;

public enum RecipeType {
    BREAKFAST("Breakfast"), LUNCH("Lunch"), DESERT("Desert"),
    APPETIZER("Appetizer"), BAKED("Baked"), SOUP("Soup"),
    VEGETARIAN("Vegetarian");

    private final String recipeTypeValue;
    RecipeType(String value) {
        this.recipeTypeValue = value;
    }

    public String getRecipeTypeValue(){
        return recipeTypeValue;
    }
}
