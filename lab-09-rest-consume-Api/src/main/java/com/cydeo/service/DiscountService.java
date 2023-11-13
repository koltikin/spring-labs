package com.cydeo.service;

import com.cydeo.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {

    List<DiscountDTO> findAllDiscounts();

    DiscountDTO createDiscount(DiscountDTO discount);

    DiscountDTO updateDiscount(DiscountDTO discount);

    DiscountDTO findByName(String name);
}
