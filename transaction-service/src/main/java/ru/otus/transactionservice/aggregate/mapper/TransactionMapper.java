package ru.otus.transactionservice.aggregate.mapper;

import org.mapstruct.Mapper;
import ru.otus.transactionservice.aggregate.DTO.TransactionDto;
import ru.otus.transactionservice.aggregate.entity.Transaction;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: transaction-service
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto toDto(Transaction entity);

    Transaction toEntity(TransactionDto dto);

    List<TransactionDto> toDto(List<Transaction> entities);
}
