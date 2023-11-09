package com.cydeo.controller;

import com.cydeo.dto.OrderDTO;
import com.cydeo.enums.PaymentMethod;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllOrderList(){
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

    @PostMapping
    public ResponseEntity<ResponseWrapper> createOrder(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Orders are successfully retrieved")
                        .data(orderService.createOrder(orderDTO))
                        .code(201).build()

        );
    }
    @GetMapping("/paymentMethod/{paymentMethod}")
    public ResponseEntity<ResponseWrapper> getAllOrdersByPaymentMethod(@PathVariable("paymentMethod")
                                                                           PaymentMethod paymentMethod){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Orders are successfully retrieved")
                        .data(orderService.findAllOrdersByPaymentMethod(paymentMethod))
                        .code(HttpStatus.OK.value()).build()
        );
    }

}
