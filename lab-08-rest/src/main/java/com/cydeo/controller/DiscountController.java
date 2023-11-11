package com.cydeo.controller;

import com.cydeo.dto.DiscountDTO;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ResponseWrapper> createDiscount(@RequestBody DiscountDTO discount){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("Discount created")
                        .code(HttpStatus.CREATED.value())
                        .data(discountService.createDiscount(discount)).build()
        );
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateDiscount(@RequestBody DiscountDTO discount){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("Discount updated")
                        .code(HttpStatus.ACCEPTED.value())
                        .data(discountService.updateDiscount(discount)).build()
        );
    }
    @GetMapping("/{name}")
    public ResponseEntity<ResponseWrapper> getDiscountByName(@PathVariable("name") String name){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("Discount is successfully retrieved")
                        .code(HttpStatus.ACCEPTED.value())
                        .data(discountService.findByName(name)).build()
        );
    }
}
