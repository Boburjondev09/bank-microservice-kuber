package ru.otus.currencyservice.endpoint;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.currencyservice.aggregate.DTO.ExchangeRateDto;
import ru.otus.currencyservice.service.business.ExchangeRateInterface;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description  PRODUCT
 */
@RestController
@RequestMapping("/api/rate/exchange")
public class ExchangeRateEndpoint {
    private final ExchangeRateInterface exchangeRateInterface;
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateEndpoint.class);

    public ExchangeRateEndpoint(ExchangeRateInterface exchangeRateInterface) {
        this.exchangeRateInterface = exchangeRateInterface;
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllExchangeRate() {
        return ResponseEntity.ok(exchangeRateInterface.findAll());
    }


    @GetMapping("/get")
    public ResponseEntity<ExchangeRateDto> getRate(@RequestParam String from,
                                                   @RequestParam String to) {
        LOG.info("GET /api/v1/rate/condition/get?from={}&to={}", from, to);
        return ResponseEntity.ok(exchangeRateInterface.getRate(from, to));
    }

    @PostMapping("/save")
    public ResponseEntity<ExchangeRateDto> saveExchangeRate(@RequestBody ExchangeRateDto dto) {
        LOG.info("POST /api/v1/rate/condition/save : {}", dto);
        return ResponseEntity.ok(exchangeRateInterface.save(dto));
    }

}
