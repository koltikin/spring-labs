package com.cydeo.controller;

import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    ResponseEntity<ResponseWrapper> getAllCustomerList(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("Customers are successfully retrieved")
                        .success(true)
                        .code(HttpStatus.ACCEPTED.value())
                        .data(customerService.findAllCustomers()).build()
        );
    }
}
