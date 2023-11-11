package com.cydeo.service.Impl;

import com.cydeo.dto.CustomerDTO;
import com.cydeo.entity.Customer;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CustomerRepository;
import com.cydeo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final MapperUtil mapper;
    @Override
    public List<CustomerDTO> findAllCustomers() {
         return  repository.findAll().stream()
                 .map(customer -> mapper.convert(customer,new CustomerDTO()))
                 .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
         repository.save(mapper.convert(customer,new Customer()));
         return customer;
    }
}
