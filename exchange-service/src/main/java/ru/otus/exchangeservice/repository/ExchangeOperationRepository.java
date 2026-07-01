package ru.otus.exchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.exchangeservice.aggregate.entity.ExchangeOperation;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: exchange-service
 */
@Repository
public interface ExchangeOperationRepository extends JpaRepository<ExchangeOperation, Long> {
    List<ExchangeOperation> findByAccountNumber(String accountNumber);
}
