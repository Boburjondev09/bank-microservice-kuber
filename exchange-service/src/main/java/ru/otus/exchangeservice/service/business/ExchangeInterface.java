package ru.otus.exchangeservice.service.business;

import ru.otus.exchangeservice.aggregate.DTO.ConvertRequestDto;
import ru.otus.exchangeservice.aggregate.DTO.ExchangeOperationDto;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: exchange-service
 */
public interface ExchangeInterface {
    List<ExchangeOperationDto> findAll();
    ExchangeOperationDto findById(Long id);
    List<ExchangeOperationDto> findByAccountNumber(String accountNumber);
    ExchangeOperationDto convert(ConvertRequestDto request);
}
