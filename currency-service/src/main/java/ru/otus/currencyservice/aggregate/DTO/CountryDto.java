package ru.otus.currencyservice.aggregate.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.otus.currencyservice.aggregate.entity.Currency;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description NITS PRODUCT
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDto {
    private String iso2;
    private String iso3;
    private String name;
    private Currency currency;
}