package com.cydeo.proxy.impl;

import com.cydeo.module.Recipe;
import com.cydeo.proxy.ShareService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FacebookShareService implements ShareService {
    @Override
    public void share(Recipe recipe) {
        System.out.println("The Recipe: " + recipe.getName() + " is shared on FaceBook.");
    }
}
