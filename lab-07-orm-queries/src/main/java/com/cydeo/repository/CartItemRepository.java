package com.cydeo.repository;


import com.cydeo.Entity.Cart;
import com.cydeo.Entity.CartItem;
import com.cydeo.enums.CartState;
import com.cydeo.enums.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    //Write a derived query to get count cart items
    int countAllByCart(Cart cart);

    //Write a derived query to get cart items for specific cart state
    List<CartItem> findAllByCart_CartState(CartState cartState);

    //Write a native query to get cart items for specific cart state and product name
    @Query(nativeQuery = true, value = "SELECT * FROM cart_item " +
            "JOIN cart ON cart_item.cart_id = cart.id " +
            "JOIN product ON cart_item.product_id = product.id " +
            "WHERE cart.cart_state = ?1 AND product.name = ?2")
    List<CartItem> getAllItemByCartStateAndProductName(CartState cartState,String productName);

    //Write a native query to get cart items for specific cart state and without discount
    @Query(nativeQuery = true,value = "SELECT * FROM cart_item " +
            "JOIN cart ON cart_item.cart_id = cart.id " +
            "JOIN discount ON cart.discount_id = discount.id " +
            "WHERE cart_state = ?1 AND discount ISNULL")
    List<CartItem> getAllItemByCartStateAndNoDiscount(CartState cartState);

    //Write a native query to get cart items for specific cart state and with specific Discount type
    @Query(nativeQuery = true,value = "SELECT * FROM cart_item JOIN cart " +
            "ON cart_item.cart_id = cart.id JOIN discount " +
            "ON cart.discount_id = discount.id WHERE cart_state =:cartState AND discount_type = :discountType")
    List<CartItem> getItemsByCartStateAndDiscountType(CartState cartState, DiscountType discountType);
}
