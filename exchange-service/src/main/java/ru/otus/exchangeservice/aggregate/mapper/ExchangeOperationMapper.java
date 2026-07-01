package ru.otus.exchangeservice.aggregate.mapper;

import org.mapstruct.Mapper;
import ru.otus.exchangeservice.aggregate.DTO.ExchangeOperationDto;
import ru.otus.exchangeservice.aggregate.entity.ExchangeOperation;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: exchange-service
 */
@Mapper(componentModel = "spring")
public interface ExchangeOperationMapper {

    ExchangeOperationDto toDto(ExchangeOperation entity);

    ExchangeOperation toEntity(ExchangeOperationDto dto);

    List<ExchangeOperationDto> toDto(List<ExchangeOperation> entities);
}
