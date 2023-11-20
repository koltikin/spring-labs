package com.cydeo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {
    private Boolean success;
    private Map<String, BigDecimal> quotes;
}
