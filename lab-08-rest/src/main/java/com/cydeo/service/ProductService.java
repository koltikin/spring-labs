package com.cydeo.service;

import com.cydeo.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductDTO> readAllProducts();

    ProductDTO updateProduct(ProductDTO productDTO);

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getProductsByCategoryIdsAndPricelessThen(List<Long> categoryId, BigDecimal price);

    ProductDTO findProductsByName(String productName);
    List<ProductDTO> findTop3ProductList();

    int findProductsPriceGreaterThan(BigDecimal price);

    List<ProductDTO> findProductListPriceGreaterThanQuantityLessThan(BigDecimal price, int quantity);
}
