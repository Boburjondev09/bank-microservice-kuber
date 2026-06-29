package ru.otus.currencyservice.aggregate.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description PRODUCT
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyDto {
    private String code;
    private String name;
    private String symbol;
    private Integer numericCode;
    private Integer fractionDigits;
    private Boolean active;
}