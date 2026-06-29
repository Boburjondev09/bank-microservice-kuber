package ru.otus.currencyservice.service.business;

import ru.otus.currencyservice.aggregate.DTO.CountryDto;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description PRODUCT
 */

public interface CountryInterface {

    List<CountryDto> findAll();

    CountryDto findById(Long id);

    CountryDto save(CountryDto dto);

    CountryDto update(Long id, CountryDto dto);

    void delete(Long id);
}
