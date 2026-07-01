package ru.otus.exchangeservice.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.exchangeservice.aggregate.DTO.ConvertRequestDto;
import ru.otus.exchangeservice.aggregate.DTO.ExchangeOperationDto;
import ru.otus.exchangeservice.service.business.ExchangeInterface;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: exchange-service
 */
@RestController
@RequestMapping("/api/exchange")
public class ExchangeEndpoint {

    private final ExchangeInterface exchangeInterface;
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeEndpoint.class);

    public ExchangeEndpoint(ExchangeInterface exchangeInterface) {
        this.exchangeInterface = exchangeInterface;
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllOperations() {
        return ResponseEntity.ok(exchangeInterface.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ExchangeOperationDto> getOperationById(@PathVariable Long id) {
        LOG.info("GET /api/exchange/get/{}", id);
        return ResponseEntity.ok(exchangeInterface.findById(id));
    }

    @GetMapping("/get/account/{accountNumber}")
    public ResponseEntity<?> getOperationsByAccount(@PathVariable String accountNumber) {
        LOG.info("GET /api/exchange/get/account/{}", accountNumber);
        return ResponseEntity.ok(exchangeInterface.findByAccountNumber(accountNumber));
    }

    @PostMapping("/convert")
    public ResponseEntity<ExchangeOperationDto> convert(@RequestBody ConvertRequestDto request) {
        LOG.info("POST /api/exchange/convert : {}", request);
        return ResponseEntity.ok(exchangeInterface.convert(request));
    }
}
