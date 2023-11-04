package com.cydeo.repository;

import com.cydeo.Entity.Address;
import com.cydeo.Entity.Category;
import com.cydeo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    //Write a derived query to get top 3 product order by price desc
    List<Product> findTop3ByOrderByPriceDesc();

    //Write a derived query to get product by specific name
    Product findFirstByName(String name);

    //Write a derived query to get product by specific category

    @Query(nativeQuery = true, value = "SELECT * FROM product p " +
            "JOIN product_category_rel pcr ON p.id = pcr.p_id " +
            "JOIN category c ON pcr.c_id = c.id " +
            "WHERE c.name = ?1")
    List<Product> findByCategoryName(String categoryName);

    //Write a derived query to get count by price greater than specific amount
    int countAllByPriceGreaterThan(BigDecimal amount);

    //Write a derived query to get all product by quantity greater than or equal specific count
    List<Product> findAllByQuantityGreaterThanEqual(int count);

    //Write a native query to get all product by price greater than specific amount and quantity lower than specific count
    @Query(nativeQuery = true, value = "SELECT * FROM product p WHERE p.price>?1 AND p.quantity<?2")
    List<Product> getAllPriceGraterThanQuantityLessThan(BigDecimal price, int count);

    //Write a native query to get all product by specific categoryId
    @Query(nativeQuery = true, value = "SELECT * FROM product p JOIN product_category_rel pcr " +
            "ON p.id = pcr.p_id WHERE pcr.c_id = ?1")
    List<Product> getAllByCategoryId(Long id);

    //Write a native query to get all product by specific categoryId and price greater than specific amount

    @Query(nativeQuery = true, value = "SELECT * FROM product JOIN product_category_rel pcr " +
            "ON product.id = pcr.p_id WHERE pcr.c_id = ?1 AND product.price>?2")
    List<Product> getAllByCategoryIdAndPriceGraterThan(Long categoryId, BigDecimal price);
}
