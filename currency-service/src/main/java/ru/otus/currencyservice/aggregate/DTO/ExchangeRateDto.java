package ru.otus.currencyservice.aggregate.DTO;

import lombok.Builder;
import lombok.Data;
import ru.otus.currencyservice.aggregate.entity.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description PRODUCT
 */
@Data
@Builder
public class ExchangeRateDto {
     private Currency baseCurrency;

     private Currency targetCurrency;

    private BigDecimal rate;

    private String provider;

    private LocalDateTime updatedAt;
}
