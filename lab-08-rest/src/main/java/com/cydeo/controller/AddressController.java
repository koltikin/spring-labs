package com.cydeo.controller;

import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllAddress(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Address are successfully retrieved")
                        .code(HttpStatus.ACCEPTED.value())
                        .data(addressService.findAllAddress()).build()
        );

    }
}
