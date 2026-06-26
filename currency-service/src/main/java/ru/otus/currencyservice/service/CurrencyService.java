package ru.otus.currencyservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.currencyservice.aggregate.entity.Currency;
import ru.otus.currencyservice.aggregate.DTO.CurrencyDto;
import ru.otus.currencyservice.aggregate.mapper.CountryMapper;
import ru.otus.currencyservice.aggregate.mapper.CurrencyMapper;
import ru.otus.currencyservice.repository.CountryRepository;
import ru.otus.currencyservice.repository.CurrencyRepository;
import ru.otus.currencyservice.service.business.CurrencyInterface;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description NITS PRODUCT
 */
@Service
public class CurrencyService implements CurrencyInterface {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyService.class);
    private final CurrencyRepository repository;
    private final CurrencyMapper mapper;

    public CurrencyService(CurrencyRepository repository, CurrencyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyDto> findAll() {
        LOG.info("Finding all currencies");

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CurrencyDto findByCode(String code) {
        LOG.info("REQUEST: (findByCode) : {}", code);
        return repository.findByCode(code)
                .map(mapper::toDto)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Currency not found: " + code));
    }

    @Override
    public CurrencyDto save(CurrencyDto dto) {
        LOG.info("REQUEST: (save) : {} ", dto);
        Currency entity = mapper.toEntity(dto);

        Currency saved = repository.save(entity);

        return mapper.toDto(saved);
    }

    @Override
    public CurrencyDto update(Long id, CurrencyDto dto) {
        LOG.info("REQUEST: (update) : id {}: dto {}", id , dto);

        Currency entity = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Currency not found: " + id));

        mapper.updateEntity(dto, entity);

        Currency updated = repository.save(entity);

        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
    LOG.info("REQUEST: (delete) : id {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException(
                    "Currency not found: " + id);
        }

        repository.deleteById(id);
    }
}
