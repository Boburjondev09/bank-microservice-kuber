package ru.otus.accountservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.accountservice.aggregate.DTO.AccountDto;
import ru.otus.accountservice.aggregate.entity.Account;
import ru.otus.accountservice.aggregate.entity.AccountStatus;
import ru.otus.accountservice.aggregate.mapper.AccountMapper;
import ru.otus.accountservice.repository.AccountRepository;
import ru.otus.accountservice.service.business.AccountInterface;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: account-service
 */
@Service
public class AccountService implements AccountInterface {

    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository repository;
    private final AccountMapper mapper;

    public AccountService(AccountRepository repository, AccountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> findAll() {
        LOG.info("Finding all accounts");
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto findById(Long id) {
        LOG.info("REQUEST: (findById) : {}", id);
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Account not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto findByAccountNumber(String accountNumber) {
        LOG.info("REQUEST: (findByAccountNumber) : {}", accountNumber);
        return repository.findByAccountNumber(accountNumber)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
    }

    @Override
    @Transactional
    public AccountDto save(AccountDto dto) {
        LOG.info("REQUEST: (save) : {}", dto);
        Account entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setStatus(AccountStatus.ACTIVE);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public AccountDto update(Long id, AccountDto dto) {
        LOG.info("REQUEST: (update) : id {}: dto {}", id, dto);
        Account entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found: " + id));
        mapper.updateEntity(dto, entity);
        entity.setUpdatedAt(LocalDateTime.now());
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOG.info("REQUEST: (delete) : id {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("Account not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public AccountDto deposit(String accountNumber, BigDecimal amount) {
        LOG.info("REQUEST: (deposit) : accountNumber {}, amount {}", accountNumber, amount);
        Account account = repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
        account.setBalance(account.getBalance().add(amount));
        account.setUpdatedAt(LocalDateTime.now());
        return mapper.toDto(repository.save(account));
    }

    @Override
    @Transactional
    public AccountDto withdraw(String accountNumber, BigDecimal amount) {
        LOG.info("REQUEST: (withdraw) : accountNumber {}, amount {}", accountNumber, amount);
        Account account = repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds for account: " + accountNumber);
        }
        account.setBalance(account.getBalance().subtract(amount));
        account.setUpdatedAt(LocalDateTime.now());
        return mapper.toDto(repository.save(account));
    }
}
