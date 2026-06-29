package ru.otus.currencyservice.service.business;

import ru.otus.currencyservice.aggregate.DTO.ExchangeRateDto;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description PRODUCT
 */
public interface ExchangeRateInterface {

    ExchangeRateDto getRate(String from, String to);

    ExchangeRateDto save(ExchangeRateDto dto);

    List<ExchangeRateDto> findAll();
}
