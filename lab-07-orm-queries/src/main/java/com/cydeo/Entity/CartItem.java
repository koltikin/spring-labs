package com.cydeo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Data
public class CartItem extends BaseEntity {

    private int quantity;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;
}
