package ru.otus.currencyservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.currencyservice.aggregate.DTO.ExchangeRateDto;
import ru.otus.currencyservice.aggregate.entity.ExchangeRate;
import ru.otus.currencyservice.aggregate.mapper.ExchangeRateMapper;
import ru.otus.currencyservice.repository.ExchangeRateRepository;
import ru.otus.currencyservice.service.business.ExchangeRateInterface;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description NITS PRODUCT
 */
@Service
public class ExchangeRateService implements ExchangeRateInterface {
    private final ExchangeRateMapper mapper;
    private final ExchangeRateRepository repository;
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateService.class);

    public ExchangeRateService(ExchangeRateMapper mapper, ExchangeRateRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public ExchangeRateDto getRate(String from, String to) {
        LOG.info("Request exchange rate: {} -> {}", from, to);

        return repository
                .findByBaseCurrencyCodeAndTargetCurrencyCode(from, to)
                .map(mapper::toDto)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Exchange rate not found: "
                                        + from + " -> " + to));
    }

    @Override
    public ExchangeRateDto save(ExchangeRateDto dto) {

        LOG.info("Saving exchange rate");

        ExchangeRate entity = mapper.toEntity(dto);

        ExchangeRate saved = repository.save(entity);

        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExchangeRateDto> findAll() {
        LOG.info("Finding all exchange rates");
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}