package com.cydeo.service;

import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<AddressDTO> findAddressList();

    OrderDTO updateOrder(OrderDTO orderDTO);
}
