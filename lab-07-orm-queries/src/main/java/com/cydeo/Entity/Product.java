package com.cydeo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class Product extends BaseEntity{
    private String name;
    private BigDecimal price;
    private int quantity;
    private int remainingQuantity;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Cart cart;
    @OneToOne
    private Payment payment;

}
