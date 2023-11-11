package com.cydeo.controller;

import com.cydeo.dto.CustomerDTO;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Customer Controller CURD operations")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllCustomerList(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("Customers are successfully retrieved")
                        .success(true)
                        .code(HttpStatus.ACCEPTED.value())
                        .data(customerService.findAllCustomers()).build()
        );
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createCustomer(@RequestBody CustomerDTO customer){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("customer created")
                        .success(true)
                        .code(HttpStatus.CREATED.value())
                        .data(customerService.createCustomer(customer)).build()
        );
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateCustomer(@RequestBody CustomerDTO customer){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("customer updated")
                        .success(true)
                        .code(HttpStatus.ACCEPTED.value())
                        .data(customerService.updateCustomer(customer)).build()
        );
    }

    @PutMapping("/{email}")
    public ResponseEntity<ResponseWrapper> updateCustomer(@PathVariable("email") String customerEmail){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .message("customer is retrieved")
                        .success(true)
                        .code(HttpStatus.ACCEPTED.value())
                        .data(customerService.findCustomerByEmail(customerEmail)).build()
        );
    }
}
