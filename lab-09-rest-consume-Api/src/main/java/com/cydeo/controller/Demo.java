package com.cydeo.controller;

import com.cydeo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class Demo {
    private final OrderService orderService;
    @GetMapping("/Demo")
    public ResponseEntity<String> getInfo(){
        return ResponseEntity.ok()
                .body(orderService.getInfo());
    }
}
