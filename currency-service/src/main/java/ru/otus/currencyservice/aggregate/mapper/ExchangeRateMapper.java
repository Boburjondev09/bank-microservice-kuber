package ru.otus.currencyservice.aggregate.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.otus.currencyservice.aggregate.DTO.ExchangeRateDto;
import ru.otus.currencyservice.aggregate.entity.ExchangeRate;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description PRODUCT
 */
@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {

    ExchangeRateDto toDto(ExchangeRate entity);

    ExchangeRate toEntity(ExchangeRateDto dto);

    List<ExchangeRateDto> toDto(List<ExchangeRate> entities);

    List<ExchangeRate> toEntity(List<ExchangeRateDto> dtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ExchangeRateDto dto, @MappingTarget ExchangeRate entity);
}
