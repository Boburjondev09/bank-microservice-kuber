package ru.otus.exchangeservice.aggregate.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.otus.exchangeservice.aggregate.entity.ExchangeStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: exchange-service
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeOperationDto {
    private Long id;
    private String accountNumber;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal sourceAmount;
    private BigDecimal convertedAmount;
    private BigDecimal appliedRate;
    private ExchangeStatus status;
    private LocalDateTime createdAt;
}
