package ru.otus.currencyservice.endpoint;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.currencyservice.service.business.ExchangeRateInterface;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description NITS PRODUCT
 */
@RestController
@RequestMapping("/api/v1/rate/condition")
public class ExchangeRateEndpoint {
    private final ExchangeRateInterface exchangeRateInterface;

    public ExchangeRateEndpoint(ExchangeRateInterface exchangeRateInterface) {
        this.exchangeRateInterface = exchangeRateInterface;
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllExchangeRate() {
        return ResponseEntity.ok(exchangeRateInterface.findAll());
    }


}
