package com.cydeo.controller;

import com.cydeo.dto.OrderDTO;
import com.cydeo.enums.PaymentMethod;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
@Tag(name = "Order", description = "Order Controller CURD operations")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllOrderList(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Orders are successfully retrieved")
                        .data(orderService.findAllOrders())
                        .code(202).build()
        );
    }

    @PutMapping("{orderId}")
    public ResponseEntity<ResponseWrapper> updateOrder(@PathVariable("orderId") long orderId,
                                                       @Valid @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Orders are successfully retrieved")
                        .data(orderService.updateOrder(orderId,orderDTO))
                        .code(202).build()
        );
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createOrder(@RequestBody OrderDTO orderDTO) {
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

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseWrapper> getAllOrdersByCustomerEmail(@PathVariable("email")
                                                                       String customerEmail){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Orders are successfully retrieved")
                        .data(orderService.findAllOrdersByCustomerEmail(customerEmail))
                        .code(HttpStatus.OK.value()).build()
        );
    }

//    @GetMapping("/{orderId}")
//    public ResponseEntity<ResponseWrapper> getOrderById(@PathVariable("orderId")
//                                                                       Long orderId,
//                                                        @RequestParam(required = false,value = "currency") Optional<String> currency){
//        return orderService.findOrderById(orderId,currency);
//
//    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseWrapper> getOrderById(@PathVariable("orderId")
                                                        Long orderId,
                                                        @RequestParam(required = false,value = "currency") Optional<String> currency){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Orders is successfully retrieved")
                        .data(orderService.findOrderByIdAndCurrency(orderId,currency))
                        .code(HttpStatus.OK.value()).build()
        );
    }

}
