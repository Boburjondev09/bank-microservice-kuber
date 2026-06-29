package ru.otus.currencyservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.currencyservice.aggregate.DTO.ExchangeRateDto;
import ru.otus.currencyservice.aggregate.entity.Currency;
import ru.otus.currencyservice.aggregate.entity.ExchangeRate;
import ru.otus.currencyservice.aggregate.mapper.ExchangeRateMapper;
import ru.otus.currencyservice.repository.CurrencyRepository;
import ru.otus.currencyservice.repository.ExchangeRateRepository;
import ru.otus.currencyservice.service.business.ExchangeRateInterface;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description PRODUCT
 */
@Service
public class ExchangeRateService implements ExchangeRateInterface {
    private final ExchangeRateMapper mapper;
    private final ExchangeRateRepository repository;
    private final CurrencyRepository currencyRepository;
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateService.class);

    public ExchangeRateService(ExchangeRateMapper mapper, ExchangeRateRepository repository, CurrencyRepository currencyRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.currencyRepository = currencyRepository;
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
    @Transactional
    public ExchangeRateDto save(ExchangeRateDto dto) {

        LOG.info("REQUEST: (save exchange rate) : {}", dto);

        // 1. Маппим базовые поля (rate, provider, updatedAt)
        ExchangeRate entity = mapper.toEntity(dto);

        // 3. ИСПОЛЬЗУЕМ currencyRepository ДЛЯ ПОИСКА ВАЛЮТ
        Currency base = currencyRepository.findByCode(dto.getBaseCurrency().getCode())
                .orElseThrow(() -> new RuntimeException("Base currency not found: " + dto.getBaseCurrency().getCode()));

        Currency target = currencyRepository.findByCode(dto.getTargetCurrency().getCode())
                .orElseThrow(() -> new RuntimeException("Target currency not found: " + dto.getTargetCurrency().getCode()));

        // 4. Устанавливаем существующие валюты в сущность курса
        entity.setBaseCurrency(base);
        entity.setTargetCurrency(target);

        // 5. Сохраняем в БД
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