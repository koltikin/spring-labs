package com.cydeo.repository;
import com.cydeo.Entity.Address;
import com.cydeo.Entity.Cart;
import com.cydeo.Entity.CartItem;
import com.cydeo.Entity.Customer;
import com.cydeo.enums.CartState;
import com.cydeo.enums.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    //Write a derived query to get all cart by specific discount type
    List<Cart> findAllByDiscount_DiscountType(DiscountType type);

    //Write a JPQL query to get all cart by customer
    @Query("SELECT c FROM Cart c WHERE c.customer = ?1")
    List<Cart> getAllCartByCustomer();

    //Write a derived query to get all cart by customer and cart state
    List<Cart> findAllByCustomerAndCartState(Customer customer, CartState state);

    //Write a derived query to get all cart by customer and cart state and discount is null condition
    List<Cart> findAllByCustomerAndCartStateAndDiscountIsNull(Customer customer,CartState state);

    //Write a native query to get all cart by customer and cart state and discount is not null condition
    @Query(nativeQuery = true, value = "SELECT * FROM cart JOIN customer " +
            "ON cart.customer_id = customer.id JOIN discount " +
            "ON cart.discount_id = discount.id WHERE customer = ?1 AND cart_state = ?2 " +
            "AND discount ISNULL")
    List<Cart> getAllCartByCustomerAndCartStateAndNoDiscount(Customer customer,CartState state);










}
