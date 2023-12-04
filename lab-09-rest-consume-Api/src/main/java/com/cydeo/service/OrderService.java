package com.cydeo.service;

import com.cydeo.dto.OrderDTO;
import com.cydeo.enums.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> findAllOrders();

    OrderDTO updateOrder(long orderId,OrderDTO orderDTO);

    OrderDTO createOrder(OrderDTO orderDTO);

    List<OrderDTO> findAllOrdersByPaymentMethod(PaymentMethod paymentMethod);

    List<OrderDTO> findAllOrdersByCustomerEmail(String customerEmail);

    OrderDTO findOrderByIdAndCurrency(Long orderId, Optional<String> currency);

    String getInfo();
}
