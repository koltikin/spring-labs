package com.cydeo.service.Impl;

import com.cydeo.FeinClient.CurrencyClient;
import com.cydeo.dto.OrderDTO;
import com.cydeo.entity.Order;
import com.cydeo.enums.PaymentMethod;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.OrderRepository;
import com.cydeo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderListImpl implements OrderService {
    private final OrderRepository repository;
    private final MapperUtil mapper;
    private final CurrencyClient currencyClient;
    @Value("${currency-api-access-key}")
    private final String access_key;
    @Override
    public List<OrderDTO> findAllOrders() {
        return repository.findAll().stream()
                .map(order -> mapper.convert(order,new OrderDTO()))
                .collect(Collectors.toList());
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

    @Override
    public OrderDTO findOrderById(Long orderId, String currency) {
        OrderDTO orderDTO = mapper.convert(repository.findById(orderId),new OrderDTO());
        Map<String,Object> map = currencyClient.currencyConvert(access_key,
                "USD",currency, BigDecimal.ONE,1);
//        System.out.println(map.get("result") +"=>"+ map.get("result").getClass().getName());
        if (Boolean.parseBoolean(map.get("success").toString())){
            BigDecimal rate = new BigDecimal(map.get("result").toString());
//            System.out.println(rate);
            orderDTO.setPaidPrice(rate.multiply(orderDTO.getPaidPrice()));
            orderDTO.setTotalPrice(rate.multiply(orderDTO.getTotalPrice()));
        }
        return orderDTO;
    }
}
