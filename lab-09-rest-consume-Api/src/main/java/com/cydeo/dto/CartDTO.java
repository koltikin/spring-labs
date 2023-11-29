package com.cydeo.dto;

import com.cydeo.entity.Discount;
import com.cydeo.enums.CartState;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CartDTO {

        private Long id;
        private CustomerDTO customer;
        private Discount discount;
        private CartState cartState;

}
