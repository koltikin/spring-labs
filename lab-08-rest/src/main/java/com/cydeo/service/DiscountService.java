package com.cydeo.service;

import com.cydeo.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {

    List<DiscountDTO> findAllDiscounts();
}
