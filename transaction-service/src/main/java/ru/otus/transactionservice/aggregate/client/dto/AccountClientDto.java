package ru.otus.transactionservice.aggregate.client.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: transaction-service
 * @description account-service AccountDto for Feign deserialization
 */
@Data
public class AccountClientDto {
    private Long id;
    private String accountNumber;
    private String ownerName;
    private String currencyCode;
    private BigDecimal balance;
    private String status;
}
