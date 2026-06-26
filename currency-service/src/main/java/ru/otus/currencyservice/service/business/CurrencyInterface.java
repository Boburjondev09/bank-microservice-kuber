package ru.otus.currencyservice.service.business;

import ru.otus.currencyservice.aggregate.DTO.CurrencyDto;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description NITS PRODUCT
 */
public interface CurrencyInterface {
    List<CurrencyDto> findAll();

    CurrencyDto findByCode(String code);

    CurrencyDto save(CurrencyDto dto);

    CurrencyDto update(Long id, CurrencyDto dto);

    void delete(Long id);
}
