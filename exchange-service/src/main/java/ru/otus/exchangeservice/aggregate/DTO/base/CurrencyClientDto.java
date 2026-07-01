package ru.otus.exchangeservice.aggregate.DTO.base;

import lombok.Data;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: exchange-service
 */
@Data
public class CurrencyClientDto {
    private Long id;
    private String code;
    private String name;
}
