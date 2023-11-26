package com.cydeo.service.Impl;

import com.cydeo.FeinClient.CurrencyClient;
import com.cydeo.dto.CurrencyDTO;
import com.cydeo.dto.OrderDTO;
import com.cydeo.entity.Order;
import com.cydeo.enums.Currency;
import com.cydeo.enums.PaymentMethod;
import com.cydeo.exception.CurrencyInvalidException;
import com.cydeo.exception.OrderNotFoundException;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.repository.OrderRepository;
import com.cydeo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
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
    public ResponseEntity<ResponseWrapper> findOrderById(Long orderId, Optional<String> currency) {
        OrderDTO orderDTO = mapper.convert(repository.findById(orderId),new OrderDTO());
        String actualCurrency = currency.orElse("");
        CurrencyDTO currencyDTO = currencyClient.getCurrency(access_key,
                    "USD", actualCurrency, 1);
//        System.out.println(map.get("result") +"=>"+ map.get("result").getClass().getName());
//        System.out.println(currencyDTO.getQuotes().get("USD"+currency) +"=>"+ currencyDTO.getQuotes().get("USD"+currency).getClass().getName());
        if (currencyDTO.getSuccess()){
            BigDecimal rate = currencyDTO.getQuotes().get("USD"+currency);
            if (rate !=null) {
                orderDTO.setPaidPrice(rate.multiply(orderDTO.getPaidPrice()));
                orderDTO.setTotalPrice(rate.multiply(orderDTO.getTotalPrice()));
               return ResponseEntity.ok(ResponseWrapper.builder()
                       .success(true)
                       .message("Orders is successfully retrieved")
                       .code(HttpStatus.OK.value())
                       .data(orderDTO).build());
            }
            else return ResponseEntity.ok(ResponseWrapper.builder()
                    .success(true)
                    .message("Orders is successfully retrieved")
                    .code(HttpStatus.OK.value())
                    .data(orderDTO).build());
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ResponseWrapper.builder()
                        .success(false)
                        .message("currency rate for "+currency.get()+" could not be found")
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .timestamp(LocalDateTime.now()).build());
    }

    @Override
    public OrderDTO findOrderByIdAndCurrency(Long orderId, Optional<String> currency) {

        Order foundOrder = repository.findById(orderId).
                orElseThrow(()-> new OrderNotFoundException("No Order Found!"));

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
