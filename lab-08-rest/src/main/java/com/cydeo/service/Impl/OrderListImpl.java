package com.cydeo.service.Impl;

import com.cydeo.dto.OrderDTO;
import com.cydeo.entity.Order;
import com.cydeo.enums.PaymentMethod;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.OrderRepository;
import com.cydeo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderListImpl implements OrderService {
    private final OrderRepository repository;
    private final MapperUtil mapper;
    @Override
    public List<OrderDTO> findAddressList() {
//        return repository.findAll().stream()
//                .map(order -> mapper.convert(order,new OrderDTO()))
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        repository.save(mapper.convert(orderDTO, new Order()));
        return orderDTO;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        repository.save(mapper.convert(orderDTO,new Order()));
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findAllOrdersByPaymentMethod(PaymentMethod paymentMethod) {
        return repository.findAllByPayment_PaymentMethod(paymentMethod).stream()
                .map(order -> mapper.convert(order,new OrderDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findAllOrdersByCustomerEmail(String customerEmail) {
        return repository.findAllByCustomer_Email(customerEmail).stream()
                .map(order -> mapper.convert(order,new OrderDTO()))
                .collect(Collectors.toList());
    }
}
