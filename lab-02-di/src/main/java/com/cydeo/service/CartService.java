package com.cydeo.service;

import com.cydeo.model.Cart;
import com.cydeo.model.Product;
import org.springframework.stereotype.Component;

@Component
public interface CartService {
    Cart addCart(Product product, int quantity);
}
