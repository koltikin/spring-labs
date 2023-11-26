package com.cydeo.service.Impl;

import com.cydeo.FeinClient.CurrencyClient;
import com.cydeo.dto.OrderDTO;
import com.cydeo.entity.Order;
import com.cydeo.enums.Currency;
import com.cydeo.enums.PaymentMethod;
import com.cydeo.exception.CurrencyInvalidException;
import com.cydeo.exception.OrderNotFoundException;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.OrderRepository;
import com.cydeo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
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
    public OrderDTO updateOrder(long orderId,OrderDTO orderDTO) {
        if (!repository.existsById(orderId)) {
            throw new OrderNotFoundException("No order was found with id "+ orderId);
        }

        Order orderToBeUpdated = mapper.convert(orderDTO,new Order());
        orderToBeUpdated.setId(orderId);

        var updatedOrder = repository.save(orderToBeUpdated);
        return mapper.convert(updatedOrder, new OrderDTO());
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
    public OrderDTO findOrderByIdAndCurrency(Long orderId, Optional<String> currency) {

        Order foundOrder = repository.findById(orderId).
                orElseThrow(()-> new OrderNotFoundException("No order was found with id "+ orderId));

       OrderDTO orderToReturn = mapper.convert(foundOrder,new OrderDTO());

        BigDecimal currencyRate = getCurrencyRate(currency);

        orderToReturn.setPaidPrice(convertCurrency(foundOrder.getPaidPrice(),currencyRate));
        orderToReturn.setTotalPrice(convertCurrency(foundOrder.getTotalPrice(),currencyRate));

        return orderToReturn;

    }

    private BigDecimal getCurrencyRate(Optional<String> currency){
        if (currency.isPresent() && !currency.get().equalsIgnoreCase("USD")){
            validateCurrency(currency);
            return currencyClient.getCurrency(access_key, currency.get()).getBody().getQuotes().get("USD"+currency.get());
        }
        return BigDecimal.ONE;
    }

    private BigDecimal convertCurrency(BigDecimal price, BigDecimal rate){
        return price.multiply(rate).setScale(2, RoundingMode.CEILING);
    }

    void validateCurrency(Optional<String> currency){
//        List<String> currencies = Arrays.stream(Currency.values())
//                .map(eachCurrency-> eachCurrency.value).collect(Collectors.toList());
//        boolean validCurrency = currencies.contains((currency.get().toUpperCase()));

        boolean validCurrency = Arrays.stream(Currency.values())
                .map(eachCurrency-> eachCurrency.value).
                anyMatch(eachCurrency->eachCurrency.equals(currency.get().toUpperCase()));

        if (!validCurrency){
            throw new CurrencyInvalidException("Invalid Currency!");
        }
    }
}
