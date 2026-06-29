package ru.otus.currencyservice.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.currencyservice.aggregate.DTO.CountryDto;
import ru.otus.currencyservice.service.business.CountryInterface;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description PRODUCT
 */
@RestController
@RequestMapping("/api/country")
public class CountryEndpoint {

    private final CountryInterface countryInterface;
    private static final Logger LOG = LoggerFactory.getLogger(CountryEndpoint.class);

    public CountryEndpoint(CountryInterface countryInterface) {
        this.countryInterface = countryInterface;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<?>> getAllCountries() {
        LOG.info("GET All Countries ");
        return ResponseEntity.ok(countryInterface.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable Long id) {
        LOG.info("GET /api/country/get/{}", id);
        return ResponseEntity.ok(countryInterface.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCountry(@RequestBody CountryDto dto) {
        LOG.info("POST /api/country/save : {}", dto);
        return ResponseEntity.ok(countryInterface.save(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable Long id,
                                           @RequestBody CountryDto dto) {
        LOG.info("PUT /api/country/update/{}", id);
        return ResponseEntity.ok(countryInterface.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        LOG.info("DELETE /api/country/delete/{}", id);
        countryInterface.delete(id);
        return ResponseEntity.noContent().build();
    }
}