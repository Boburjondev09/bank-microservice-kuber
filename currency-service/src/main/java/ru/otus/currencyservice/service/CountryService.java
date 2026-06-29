package ru.otus.currencyservice.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.currencyservice.aggregate.DTO.CountryDto;
import ru.otus.currencyservice.aggregate.entity.Country;
import ru.otus.currencyservice.aggregate.entity.Currency;
import ru.otus.currencyservice.aggregate.mapper.CountryMapper;
import ru.otus.currencyservice.repository.CountryRepository;
import ru.otus.currencyservice.repository.CurrencyRepository;
import ru.otus.currencyservice.service.business.CountryInterface;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description PRODUCT
 */
@Service
public class CountryService implements CountryInterface {

    private final CountryRepository countryRepository;
    private final CurrencyRepository currencyRepository;
    private final CountryMapper mapper;
    private static final Logger LOG = LoggerFactory.getLogger(CountryService.class);

    public CountryService(CountryRepository countryRepository, CurrencyRepository currencyRepository, CountryMapper mapper) {
        this.countryRepository = countryRepository;
        this.currencyRepository = currencyRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CountryDto> findAll() {
        LOG.info("REQUEST: (findAll) ");
        return mapper.toDto(countryRepository.findAll());
    }

    @Override
    public CountryDto findById(Long id) {
        LOG.info("REQUEST: (findByID) ");
        Country country = countryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Country not found: " + id));
        LOG.info("RESPONSE: (findByID) : {}", id);
        return mapper.toDto(country);
    }

    @Transactional
    @Override
    public CountryDto save(CountryDto dto) {
        LOG.info("REQUEST: (save) : country dto {}", dto);
        // 1. Маппим текстовые поля (iso2, iso3, name)
        Country entity = mapper.toEntity(dto);

        // 2. Находим привязанную валюту из БД, чтобы не создавать дубликат и не слать null
        if (dto.getCurrency() != null && dto.getCurrency().getCode() != null) {
            Currency currency = currencyRepository.findByCode(dto.getCurrency().getCode())
                    .orElseThrow(() -> new RuntimeException("Currency not found: " + dto.getCurrency().getCode()));
            entity.setCurrency(currency);
        } else {
            throw new RuntimeException("Currency information is missing in the request");
        }

        // 3. Сохраняем страну
        Country saved = countryRepository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public CountryDto update(Long id, CountryDto dto) {
        LOG.info("REQUEST: (update) ");
        Country entity = countryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Country not found: " + id));

        mapper.updateEntity(dto, entity);

        Country updated = countryRepository.save(entity);

        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        LOG.info("REQUEST: * (delete) ");
        if (!countryRepository.existsById(id)) {
            throw new RuntimeException("Country not found: " + id);
        }

        countryRepository.deleteById(id);
    }
}