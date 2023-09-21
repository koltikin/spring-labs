package com.cydeo.Entity;
import com.cydeo.enums.CartState;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class Cart extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private CartState cartState;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Discount discount;
}
