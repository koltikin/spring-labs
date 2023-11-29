package com.cydeo.converter;


import com.cydeo.dto.CustomerDTO;
import com.cydeo.service.CustomerService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class CustomerConverter implements Converter<Long, CustomerDTO> {
    private final CustomerService customerService;

    public CustomerConverter(@Lazy CustomerService customerService) {
        this.customerService = customerService;
    }
    @Override
    public CustomerDTO convert(Long customerId) {
        CustomerDTO customer = customerService.findById(customerId);
        System.out.println(customer); // check if converter works or not
        return customer;
    }
}
