package com.cydeo.repository;

import com.cydeo.Entity.Address;
import com.cydeo.Entity.Order;
import com.cydeo.enums.PaymentMethod;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    //Write a derived query to get top 5 orders by order by total price desc
    List<Order> findTop5ByOrderByTotalPriceDesc();

    //Write a derived query to get all orders by customer email

    List<Order> findAllByCustomer_Email(String email);

    //Write a derived query to get all orders by specific payment method
    List<Order> findAllByPayment_PaymentMethod(PaymentMethod method);

    //Write a derived query to check is there any orders by customer email
    Boolean existsByCustomerEmail(String email);

    //Write a native query to get all orders by specific product name
    @Query(nativeQuery = true, value = "SELECT * FROM orders JOIN cart " +
            "ON orders.cart_id = cart.id JOIN cart_item " +
            "ON cart.id = cart_item.cart_id JOIN product " +
            "ON cart_item.product_id = product.id WHERE product.name = ?1")
    List<Order> getAllOrdersByProductName(String productName);

    //Write a native query to get all orders by specific categoryId
    @Query(nativeQuery = true, value = "SELECT * FROM orders JOIN cart " +
            "ON orders.cart_id = cart.id JOIN cart_item " +
            "ON cart.id = cart_item.cart_id JOIN product " +
            "ON cart_item.product_id = product.id JOIN product_category_rel " +
            "ON product.id = product_category_rel.p_id WHERE product_category_rel.c_id = ?1")
    List<Order> getOrderByCategoryId(Long id);

    //Write a JPQL query to get all orders by totalPrice and paidPrice are equals
    @Query("SELECT o FROM Order o WHERE o.totalPrice = o.paidPrice")
    List<Order> findAllByTotalPriceIsEqualPaidPrice();

    //Write a native query to get all orders by totalPrice and paidPrice are not equals and discount is not null

    @Query(nativeQuery = true, value = "SELECT * FROM orders JOIN cart " +
            "ON orders.cart_id = cart.id WHERE cart.discount_id IS NOT NULL AND orders.paid_price = orders.total_price")
    List<Order> getAllOrdersPaidPriceAndTotalPriceEqualDiscountNotNull();








}
