package com.cydeo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Product extends BaseEntity{
    private String name;
    private BigDecimal price;
    private int quantity;
    private int remainingQuantity;

}
