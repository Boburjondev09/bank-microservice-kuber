package ru.otus.currencyservice.aggregate.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.otus.currencyservice.aggregate.DTO.CountryDto;
import ru.otus.currencyservice.aggregate.entity.Country;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 26.06.2026
 * @Project: currency-service
 * @description PRODUCT
 */
@Mapper(componentModel = "spring", uses = {CurrencyMapper.class})
public interface CountryMapper {

    CountryDto toDto(Country entity);

    Country toEntity(CountryDto dto);

    List<CountryDto> toDto(List<Country> entities);

    List<Country> toEntity(List<CountryDto> dtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(CountryDto dto, @MappingTarget Country entity);
}