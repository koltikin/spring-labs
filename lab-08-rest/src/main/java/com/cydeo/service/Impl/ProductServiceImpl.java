package com.cydeo.service.Impl;

import com.cydeo.dto.ProductDTO;
import com.cydeo.entity.Product;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ProductRepository;
import com.cydeo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final MapperUtil mapper;

    @Override
    public List<ProductDTO> readAllProducts() {
        return repository.findAll().stream()
                .map(product -> mapper.convert(product,new ProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        repository.save(mapper.convert(productDTO, new Product()));
        return mapper.convert(repository.findById(productDTO.getId()),new ProductDTO());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        repository.save(mapper.convert(productDTO,new Product()));
        return productDTO;
    }

    @Override
    public List<ProductDTO> getProductsByCategoryIdsAndPricelessThen(List<Long> categoryId, BigDecimal price) {
        return repository.retrieveProductListByCategory(categoryId,price).stream()
                .map(product->mapper.convert(product,new ProductDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findProductsByName(String productName) {
        return mapper.convert(repository.findFirstByName(productName),new ProductDTO());
    }

    @Override
    public List<ProductDTO> findTop3ProductList() {
        return repository.findTop3ByOrderByPriceDesc().stream()
                .map(product -> mapper.convert(product,new ProductDTO()))
                .collect(Collectors.toList());
    }
}
