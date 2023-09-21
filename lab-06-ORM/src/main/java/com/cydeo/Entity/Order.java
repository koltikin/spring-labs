package com.cydeo.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order extends BaseEntity {

    private BigDecimal paidPrice;
    private BigDecimal totalPrice;

    @ManyToOne
    private Customer customer;
}
