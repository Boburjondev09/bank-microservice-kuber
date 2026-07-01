package ru.otus.transactionservice.service.business;

import ru.otus.transactionservice.aggregate.DTO.TransactionDto;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: transaction-service
 */
public interface TransactionInterface {
    List<TransactionDto> findAll();
    TransactionDto findById(Long id);
    TransactionDto findByReferenceNumber(String referenceNumber);
    List<TransactionDto> findBySender(String senderAccountNumber);
    List<TransactionDto> findByReceiver(String receiverAccountNumber);
    TransactionDto transfer(TransactionDto dto);
    TransactionDto deposit(TransactionDto dto);
    TransactionDto withdrawal(TransactionDto dto);
}