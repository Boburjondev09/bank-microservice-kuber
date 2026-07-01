package ru.otus.exchangeservice.aggregate.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: exchange-service
 * @description incoming payload to perform a currency conversion for an account
 */
@Data
@Builder
public class ConvertRequestDto {
    private String accountNumber;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal amount;
}
