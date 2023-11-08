package com.cydeo.controller;


import com.cydeo.dto.ProductDTO;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ResponseWrapper> getProductList(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Products are successfully retrieved")
                        .code(HttpStatus.ACCEPTED.value())
                        .data(productService.readAllProducts())
                        .build());

    }
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Product is updated")
                        .code(HttpStatus.CREATED.value())
                        .data(productService.updateProduct(productDTO))
                        .build());

    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> createProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper("Product is created",
                        productService.createProduct(productDTO),HttpStatus.CREATED));

    }

    @PostMapping("/categoryandprice")// takes list of category id and price, return list of product that category id belong to the given list and price greater than given price.
    public ResponseEntity<ResponseWrapper> getProductListByPriceAndQuantity(@RequestBody Map<String,Object> request){
        List<Long> categoryIdList = (List<Long>) request.get("categoryIdList");
        BigDecimal price = new BigDecimal(request.get("price").toString());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseWrapper("Products are successfully retrieved",
                        productService.getProductsByCategoryIdsAndPricelessThen(categoryIdList, price),HttpStatus.OK));

    }

    @GetMapping("/{name}")
    public ResponseEntity<ResponseWrapper> getProductListByName(@PathVariable("name") String productName){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Products are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(productService.findProductsByName(productName))
                        .build());

    }
    @GetMapping("/top3")
    public ResponseEntity<ResponseWrapper> getTop3ProductList(){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true)
                        .message("Products are successfully retrieved")
                        .code(202)
                        .data(productService.findTop3ProductList())
                        .build()
        );
    }
    @GetMapping("/price/{price}") // getProducts count that price greater than given price
    public ResponseEntity<ResponseWrapper> getProductListByPrice(@PathVariable("price") BigDecimal price){
       return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true).message("Products are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(productService.findProductsPriceGreaterThan(price))
                        .build());
    }

    @GetMapping("/price/{price}/quantity/{quantity}") // getProducts the remaining quantity less than given quantity and price greater than given price
    public ResponseEntity<ResponseWrapper> getProductListByPriceAndQuantity(@PathVariable("price") BigDecimal price,
                                                                            @PathVariable("quantity") int quantity){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true).message("Products are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(productService.findProductListPriceGreaterThanQuantityLessThan(price,quantity))
                        .build());
    }

    @GetMapping("/category/{id}") // getProducts that belongs to the category which has given id
    public ResponseEntity<ResponseWrapper> getProductListByCategory(@PathVariable("id") long categoryId){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true).message("Products are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(productService.findProductBelongsToCategoryId(categoryId))
                        .build());
    }


}
