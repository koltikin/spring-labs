package com.cydeo.controller;

import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/discount")
public class DiscountController {
    private final DiscountService discountService;
    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllDiscounts(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("Discounts are successfully retrieved")
                        .code(HttpStatus.ACCEPTED.value())
                        .data(discountService.findAllDiscounts()).build()
        );
    }
}
