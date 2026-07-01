package ru.otus.accountservice.aggregate.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.otus.accountservice.aggregate.entity.AccountStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: account-service
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private Long id;
    private String accountNumber;
    private String ownerName;
    private String currencyCode;
    private BigDecimal balance;
    private AccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
