package ru.otus.accountservice.service.business;

import ru.otus.accountservice.aggregate.DTO.AccountDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: account-service
 */
public interface AccountInterface {
    List<AccountDto> findAll();
    AccountDto findById(Long id);
    AccountDto findByAccountNumber(String accountNumber);
    AccountDto save(AccountDto dto);
    AccountDto update(Long id, AccountDto dto);
    void delete(Long id);
    AccountDto deposit(String accountNumber, BigDecimal amount);
    AccountDto withdraw(String accountNumber, BigDecimal amount);
}
