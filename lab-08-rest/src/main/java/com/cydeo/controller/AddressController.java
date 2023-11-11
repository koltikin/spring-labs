package com.cydeo.controller;

import com.cydeo.dto.AddressDTO;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ResponseWrapper> createAddress(@RequestBody AddressDTO addressDTO){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("new address created")
                        .code(HttpStatus.CREATED.value())
                        .data(addressService.createAddress(addressDTO)).build()
        );
    }
}
