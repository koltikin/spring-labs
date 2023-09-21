package com.cydeo.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
public class Recipe {
    private  int id;
    private String name;
    private int duration;
    private String preparation;
    private List<Ingredients> ingredients;
    private RecipeType recipeType;
}
