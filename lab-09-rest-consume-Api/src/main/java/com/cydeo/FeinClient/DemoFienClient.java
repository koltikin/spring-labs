package com.cydeo.FeinClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(url = "http://localhost:8080/api/v1/home", name = "DEMO")
public interface DemoFienClient {
    @GetMapping
    String getInfo(@RequestHeader("Authorization") String authorizationHeader);
}
