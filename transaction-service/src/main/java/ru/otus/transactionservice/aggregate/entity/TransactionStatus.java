package ru.otus.transactionservice.aggregate.entity;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: transaction-service
 */
public enum TransactionStatus {
    PENDING,
    COMPLETED,
    FAILED,
    REVERSED
}
