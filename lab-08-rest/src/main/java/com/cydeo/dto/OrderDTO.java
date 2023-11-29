package com.cydeo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDTO {
    private Long cartId;
    private BigDecimal paidPrice;
    private BigDecimal totalPrice;
    private CustomerDTO customer;
    private Long paymentId;
    private Long id;
}
