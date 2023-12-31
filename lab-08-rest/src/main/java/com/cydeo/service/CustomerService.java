package com.cydeo.service;

import com.cydeo.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAllCustomers();

    CustomerDTO createCustomer(CustomerDTO customer);

    CustomerDTO updateCustomer(CustomerDTO customer);

    CustomerDTO findCustomerByEmail(String customerEmail);
}
