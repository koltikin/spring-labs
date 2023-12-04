package com.cydeo.FeinClient;

import com.cydeo.dto.CurrencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Map;
@Component
@FeignClient(url = "http://apilayer.net/api", name = "CURRENCY-CLIENT")
public interface CurrencyClient {
    @GetMapping("/live")
    CurrencyDTO getCurrency(@RequestParam("access_key")String access_key,
                            @RequestParam("source") String source,
                            @RequestParam("currencies") String currency,
                            @RequestParam("format") int format);

    @GetMapping("/convert")
    Map<String ,Object> currencyConvert(@RequestParam("access_key")String access_key,
                          @RequestParam("from") String source,
                          @RequestParam("to") String currency,
                          @RequestParam("amount") BigDecimal amount,
                          @RequestParam("format") int format);

    @GetMapping("/live")
    ResponseEntity<CurrencyDTO> getCurrency(@RequestParam("access_key") String access_key,
                               @RequestParam("currencies") String currency);
}
