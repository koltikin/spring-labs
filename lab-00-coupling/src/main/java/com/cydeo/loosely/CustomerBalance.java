package com.cydeo.loosely;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

public class CustomerBalance extends Balance{
    public CustomerBalance(UUID userId, BigDecimal amount) {
        super(userId, amount);
    }
    @Override
    public BigDecimal addBalance(BigDecimal amount) {
        setAmount(this.getAmount().add(amount));
        return this.getAmount();
    }
}
