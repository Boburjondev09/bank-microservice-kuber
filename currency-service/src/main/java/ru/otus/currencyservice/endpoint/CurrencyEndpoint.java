package ru.otus.currencyservice.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.currencyservice.aggregate.DTO.CurrencyDto;
import ru.otus.currencyservice.service.business.CurrencyInterface;
import ru.otus.currencyservice.util.ProjectStartingPoint;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description  PRODUCT
 */
@RestController
@RequestMapping("/api/currencies")
public class CurrencyEndpoint {

    private final CurrencyInterface currencyInterface;
    private static final Logger LOG = LoggerFactory.getLogger(CurrencyEndpoint.class);

    public CurrencyEndpoint(CurrencyInterface currencyInterface) {
        this.currencyInterface = currencyInterface;
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllCurrency() {
        return ResponseEntity.ok(currencyInterface.findAll());
    }

    @GetMapping("/get/{code}")
    public ResponseEntity<CurrencyDto> getCurrencyByCode(@PathVariable String code) {
        LOG.info("GET /api/currencies/get/{}", code);
        return ResponseEntity.ok(currencyInterface.findByCode(code));
    }

    @PostMapping("/save")
    public ResponseEntity<CurrencyDto> saveCurrency(@RequestBody CurrencyDto dto) {
        LOG.info("POST /api/currencies/save : {}", dto);
        return ResponseEntity.ok(currencyInterface.save(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CurrencyDto> updateCurrency(@PathVariable Long id,
                                                      @RequestBody CurrencyDto dto) {
        LOG.info("PUT /api/currencies/update/{}", id);
        return ResponseEntity.ok(currencyInterface.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) {
        LOG.info("DELETE /api/currencies/delete/{}", id);
        currencyInterface.delete(id);
        return ResponseEntity.noContent().build();
    }
}
