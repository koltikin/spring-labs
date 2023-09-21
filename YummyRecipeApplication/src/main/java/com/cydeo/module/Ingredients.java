package com.cydeo.module;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ingredients {
    private String name;
    private int quantity;
    private QuantityType quantityType;
}
