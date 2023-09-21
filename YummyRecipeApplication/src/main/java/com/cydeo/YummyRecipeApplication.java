package com.cydeo;

import com.cydeo.config.AuthorConfigData;
import com.cydeo.services.RecipeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class YummyRecipeApplication {

    public static void main(String[] args) {

       ApplicationContext container =  SpringApplication.run(YummyRecipeApplication.class, args);

        RecipeService recipeService = container.getBean(RecipeService.class);

        AuthorConfigData authorConfigData = container.getBean(AuthorConfigData.class);

        System.out.println("************** Author Config Data ****************");
        System.out.println("authorConfigData:\n" + authorConfigData);


        for (int i = 0; i < 100; i++) {
            System.out.println(recipeService.prepareRecipe());

        }

    }

}
