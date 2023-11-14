package com.cydeo.controller;


import com.cydeo.dto.ProductDTO;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Product", description = "Product Controller CURD operations")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @Operation(description = "This End point Return all the products as a list",summary = "Return all the products")
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
    @Operation(description = "This End point updates specific Products",summary = "Update product")
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
    @Operation(description = "Using this endpoint, you can create a product", summary = "Create product")
    public ResponseEntity<ResponseWrapper> createProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseWrapper("Product is created",
                        productService.createProduct(productDTO),HttpStatus.OK));

    }

    @PostMapping("/categoryandprice")// takes list of category id and price, return list of product that category id belong to the given list and price greater than given price.
    @Operation(description = "this end point takes list of category id and price, " +
            "return list of product that category id belong to the given list " +
            "and price greater than given price.",summary = "return list of product")
    public ResponseEntity<ResponseWrapper> getProductListByPriceAndQuantity(@RequestBody Map<String,Object> request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseWrapper.builder().message("Products are successfully retrieved")
                                .success(true)
                                .data(productService.getProductsByCategoryIdsAndPricelessThen(request))
                                .code(HttpStatus.OK.value()).build());

    }

    @GetMapping("/{name}")
    @Operation(description = "Using this end point you can find a product by product name",summary = "find product by product name")
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
    @Operation(description = "Using this end point you can get 3 most expansive product list",summary = "3 most expansive product")
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
    @Operation(description = "Using this end point you can getProducts count that price greater than given price",summary = "price greater than products")
    public ResponseEntity<ResponseWrapper> getProductListByPrice(@PathVariable("price") BigDecimal price){
       return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true).message("Products are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(productService.findProductsPriceGreaterThan(price))
                        .build());
    }

    @GetMapping("/price/{price}/quantity/{quantity}") // getProducts the remaining quantity less than given quantity and price greater than given price
    @Operation(description = "Using this end point you can getProducts the remaining quantity less than given quantity and price greater than given price",summary = "list of products")
    public ResponseEntity<ResponseWrapper> getProductListByPriceAndQuantity(@PathVariable("price") BigDecimal price,
                                                                            @PathVariable("quantity") int quantity){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true).message("Products are successfully retrieved")
                        .code(200)
                        .data(productService.findProductListPriceGreaterThanQuantityLessThan(price,quantity))
                        .build());
    }

    @GetMapping("/category/{id}") // getProducts that belongs to the category which has given id
    @Operation(description = "Using this end point you can getProducts that belongs to the category which has given id",summary = "get products by category id")
    public ResponseEntity<ResponseWrapper> getProductListByCategory(@PathVariable("id") long categoryId){
        return ResponseEntity.ok(
                ResponseWrapper.builder()
                        .success(true).message("Products are successfully retrieved")
                        .code(HttpStatus.OK.value())
                        .data(productService.findProductBelongsToCategoryId(categoryId))
                        .build());
    }


}
