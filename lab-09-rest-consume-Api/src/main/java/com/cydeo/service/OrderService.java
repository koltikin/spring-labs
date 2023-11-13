package com.cydeo.service;

import com.cydeo.dto.OrderDTO;
import com.cydeo.enums.PaymentMethod;
import com.cydeo.model.ResponseWrapper;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> findAllOrders();

    OrderDTO updateOrder(OrderDTO orderDTO);

    OrderDTO createOrder(OrderDTO orderDTO);

    List<OrderDTO> findAllOrdersByPaymentMethod(PaymentMethod paymentMethod);

    List<OrderDTO> findAllOrdersByCustomerEmail(String customerEmail);

    ResponseWrapper findOrderById(Long orderId, Optional<String> currency);
}
