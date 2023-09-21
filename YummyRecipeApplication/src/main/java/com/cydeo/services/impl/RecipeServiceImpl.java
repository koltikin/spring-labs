package com.cydeo.services.impl;

import com.cydeo.module.Ingredients;
import com.cydeo.module.QuantityType;
import com.cydeo.module.Recipe;
import com.cydeo.module.RecipeType;
import com.cydeo.proxy.ShareService;
import com.cydeo.repository.impl.RecipeRepositoryImpl;
import com.cydeo.services.RecipeService;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final ShareService shareService;
    private final RecipeRepositoryImpl repository;
    private final Faker faker;
    private final Random myRandom;

    public List<Ingredients> getListOfIngredients(){
        List<Ingredients> ingredientsList = new ArrayList<>();
//        Random myRandom = new Random();
//        Faker faker = new Faker();

        for (int i = 0; i < myRandom.nextInt(5)+5; i++) {
            Ingredients ingredient = new Ingredients();
            ingredient.setName(faker.food().ingredient());
            ingredient.setQuantity(myRandom.nextInt(25)+25);
            List<QuantityType> quantities = Arrays.asList(QuantityType.values());
            ingredient.setQuantityType(quantities.get(myRandom.nextInt(quantities.size())));

            ingredientsList.add(ingredient);
        }
        return ingredientsList;
    }
    public Boolean prepareRecipe() {

//        Random myRandom = new Random();
//        Faker faker = new Faker();

        var id = myRandom.nextInt(500);
        var recipeName = faker.food().dish();
        var duration = myRandom.nextInt(15)+25;
        var preparation = "some instructions to how to prepare";
        var ingredientsList = getListOfIngredients();
        List<RecipeType> recipeTypes = Arrays.asList(RecipeType.values());
        var recipeType = recipeTypes.get(myRandom.nextInt(recipeTypes.size()));

        Recipe recipe = new Recipe(id,recipeName,duration,preparation,ingredientsList,recipeType);


        shareService.share(recipe);

        return repository.save(recipe);
    }

}

