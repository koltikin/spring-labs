package com.cydeo.service;

import com.cydeo.dto.OrderDTO;
import com.cydeo.enums.PaymentMethod;

import java.util.List;

public interface OrderService {

    List<OrderDTO> findAllOrders();

    OrderDTO updateOrder(OrderDTO orderDTO);

    OrderDTO createOrder(OrderDTO orderDTO);

    List<OrderDTO> findAllOrdersByPaymentMethod(PaymentMethod paymentMethod);

    List<OrderDTO> findAllOrdersByCustomerEmail(String customerEmail);
}
