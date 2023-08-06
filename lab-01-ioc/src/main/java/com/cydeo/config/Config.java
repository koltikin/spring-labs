package com.cydeo.config;

import com.cydeo.Currency;
import com.cydeo.account.Current;
import com.cydeo.account.Saving;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.UUID;

@Configuration
public class Config {
    UUID userId = UUID.randomUUID();

    @Bean
    public Saving getSaving(){
        Currency currency = new Currency("122bd","Ziya");
        Saving saving =  new Saving();
        saving.setAccountId(userId);
        saving.setCurrency(currency);
        saving.setAmount(BigDecimal.TEN);

        return saving;
    }

    @Bean
    public Current getCurrent(){
        Currency currency = new Currency("122bd","Ziya");
        Current current =  new Current();
        current.setAccountId(userId);
        current.setCurrency(currency);
        current.setAmount(BigDecimal.TEN);

        return current;
    }

}
