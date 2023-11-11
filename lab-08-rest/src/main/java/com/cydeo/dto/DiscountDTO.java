package com.cydeo.dto;

import com.cydeo.enums.DiscountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DiscountDTO{
    private String name;
    private BigDecimal discount;
    private DiscountType discountType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
}
