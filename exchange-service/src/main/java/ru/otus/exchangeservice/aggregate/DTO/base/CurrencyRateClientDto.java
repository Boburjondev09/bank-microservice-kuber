package ru.otus.exchangeservice.aggregate.DTO.base;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: exchange-service
 * @description mirrors the response shape of currency-service's /api/rate/exchange/get endpoint
 */
@Data
public class CurrencyRateClientDto {
    private CurrencyClientDto baseCurrency;
    private CurrencyClientDto targetCurrency;
    private BigDecimal rate;
    private String provider;
}
