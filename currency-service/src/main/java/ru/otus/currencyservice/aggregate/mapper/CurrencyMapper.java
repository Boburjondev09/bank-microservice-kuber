package ru.otus.currencyservice.aggregate.mapper;

import org.mapstruct.*;
import ru.otus.currencyservice.aggregate.DTO.CurrencyDto;
import ru.otus.currencyservice.aggregate.entity.Currency;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description PRODUCT
 */
@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyDto toDto(Currency entity);


    Currency toEntity(CurrencyDto dto);

    List<CurrencyDto> toDto(List<Currency> entities);

    List<Currency> toEntity(List<CurrencyDto> dtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(CurrencyDto dto, @MappingTarget Currency entity);
}
