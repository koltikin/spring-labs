package com.cydeo.proxy.impl;

import com.cydeo.module.Recipe;
import com.cydeo.proxy.ShareService;
import org.springframework.stereotype.Component;

@Component
public class InstagramShareService implements ShareService {
    @Override
    public void share(Recipe recipe) {
        System.out.println("The Recipe: " + recipe.getName() + " is shared on Instagram.");
    }
}
