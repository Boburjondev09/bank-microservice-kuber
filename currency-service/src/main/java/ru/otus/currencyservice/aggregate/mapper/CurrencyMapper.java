package ru.otus.currencyservice.aggregate.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.otus.currencyservice.aggregate.DTO.CurrencyDto;
import ru.otus.currencyservice.aggregate.entity.Currency;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description NITS PRODUCT
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
