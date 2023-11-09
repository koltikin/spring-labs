package com.cydeo.service;

import com.cydeo.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> findAddressList();

    OrderDTO updateOrder(OrderDTO orderDTO);

    OrderDTO createOrder(OrderDTO orderDTO);
}
