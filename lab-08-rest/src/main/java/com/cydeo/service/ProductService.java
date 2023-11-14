package com.cydeo.service;

import com.cydeo.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductDTO> readAllProducts();

    ProductDTO updateProduct(ProductDTO productDTO);

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getProductsByCategoryIdsAndPricelessThen(Map<String,Object> request);

    ProductDTO findProductsByName(String productName);
    List<ProductDTO> findTop3ProductList();

    int findProductsPriceGreaterThan(BigDecimal price);

    List<ProductDTO> findProductListPriceGreaterThanQuantityLessThan(BigDecimal price, int quantity);

    List<ProductDTO> findProductBelongsToCategoryId(long categoryId);
}
