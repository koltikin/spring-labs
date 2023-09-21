package com.cydeo.repository.impl;

import com.cydeo.module.Recipe;
import com.cydeo.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@AllArgsConstructor
@Component
public class RecipeRepositoryImpl implements RecipeRepository {
    private List<Recipe> listOfRecipe;

    @Override
    public boolean save(Recipe recipe) {
        listOfRecipe.add(recipe);
        return true;


    }
}
