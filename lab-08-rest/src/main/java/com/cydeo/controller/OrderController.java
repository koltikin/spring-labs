package com.cydeo.controller;

import com.cydeo.dto.OrderDTO;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAddressList(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Orders are successfully retrieved")
                        .data(orderService.findAddressList())
                        .code(202).build()
        );
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateOrder(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Orders are successfully retrieved")
                        .data(orderService.updateOrder(orderDTO))
                        .code(202).build()

        );
    }
}
