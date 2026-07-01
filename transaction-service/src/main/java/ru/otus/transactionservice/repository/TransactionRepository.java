package ru.otus.transactionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.transactionservice.aggregate.entity.Transaction;
import ru.otus.transactionservice.aggregate.entity.TransactionStatus;

import java.util.List;
import java.util.Optional;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: transaction-service
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByReferenceNumber(String referenceNumber);
    List<Transaction> findBySenderAccountNumber(String senderAccountNumber);
    List<Transaction> findByReceiverAccountNumber(String receiverAccountNumber);
    List<Transaction> findByStatus(TransactionStatus status);
}
