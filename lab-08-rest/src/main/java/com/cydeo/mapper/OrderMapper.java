package com.cydeo.mapper;

import com.cydeo.dto.CustomerDTO;
import com.cydeo.dto.OrderDTO;
import com.cydeo.entity.Customer;
import com.cydeo.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO orderToOrderDTO(Order order);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
