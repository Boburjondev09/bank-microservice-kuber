package ru.otus.transactionservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.transactionservice.aggregate.DTO.TransactionDto;
import ru.otus.transactionservice.aggregate.client.AccountServiceClient;
import ru.otus.transactionservice.aggregate.entity.Transaction;
import ru.otus.transactionservice.aggregate.entity.TransactionStatus;
import ru.otus.transactionservice.aggregate.entity.TransactionType;
import ru.otus.transactionservice.aggregate.mapper.TransactionMapper;
import ru.otus.transactionservice.repository.TransactionRepository;
import ru.otus.transactionservice.service.business.TransactionInterface;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: transaction-service
 * @description orchestrates account-service (withdraw/deposit) for transfers,
 * deposits and withdrawals; persists every operation for audit.
 *
 * 1. BANK : transaction different currency
 */
@Service
public class TransactionService implements TransactionInterface {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final AccountServiceClient accountServiceClient;

    public TransactionService(TransactionRepository repository,
                               TransactionMapper mapper,
                               AccountServiceClient accountServiceClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.accountServiceClient = accountServiceClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> findAll() {
        LOG.info("Finding all transactions");
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionDto findById(Long id) {
        LOG.info("REQUEST: (findById) : {}", id);
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Transaction not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionDto findByReferenceNumber(String referenceNumber) {
        LOG.info("REQUEST: (findByReferenceNumber) : {}", referenceNumber);
        return repository.findByReferenceNumber(referenceNumber)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Transaction not found: " + referenceNumber));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> findBySender(String senderAccountNumber) {
        LOG.info("REQUEST: (findBySender) : {}", senderAccountNumber);
        return repository.findBySenderAccountNumber(senderAccountNumber)
                .stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> findByReceiver(String receiverAccountNumber) {
        LOG.info("REQUEST: (findByReceiver) : {}", receiverAccountNumber);
        return repository.findByReceiverAccountNumber(receiverAccountNumber)
                .stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional
    public TransactionDto transfer(TransactionDto dto) {
        LOG.info("REQUEST: (transfer) : {}", dto);
        Transaction tx = buildTransaction(dto, TransactionType.TRANSFER);
        try {
            accountServiceClient.withdraw(dto.getSenderAccountNumber(), dto.getAmount());
            accountServiceClient.deposit(dto.getReceiverAccountNumber(), dto.getAmount());
            tx.setStatus(TransactionStatus.COMPLETED);
        } catch (Exception ex) {
            LOG.error("Transfer failed: {}", ex.getMessage());
            tx.setStatus(TransactionStatus.FAILED);
            repository.save(tx);
            throw new RuntimeException("Transfer failed: " + ex.getMessage(), ex);
        }
        return mapper.toDto(repository.save(tx));
    }

    @Override
    @Transactional
    public TransactionDto deposit(TransactionDto dto) {
        LOG.info("REQUEST: (deposit) : {}", dto);
        Transaction tx = buildTransaction(dto, TransactionType.DEPOSIT);
        try {
            accountServiceClient.deposit(dto.getReceiverAccountNumber(), dto.getAmount());
            tx.setStatus(TransactionStatus.COMPLETED);
        } catch (Exception ex) {
            LOG.error("Deposit failed: {}", ex.getMessage());
            tx.setStatus(TransactionStatus.FAILED);
            repository.save(tx);
            throw new RuntimeException("Deposit failed: " + ex.getMessage(), ex);
        }
        return mapper.toDto(repository.save(tx));
    }

    @Override
    @Transactional
    public TransactionDto withdrawal(TransactionDto dto) {
        LOG.info("REQUEST: (withdrawal) : {}", dto);
        Transaction tx = buildTransaction(dto, TransactionType.WITHDRAWAL);
        try {
            accountServiceClient.withdraw(dto.getSenderAccountNumber(), dto.getAmount());
            tx.setStatus(TransactionStatus.COMPLETED);
        } catch (Exception ex) {
            LOG.error("Withdrawal failed: {}", ex.getMessage());
            tx.setStatus(TransactionStatus.FAILED);
            repository.save(tx);
            throw new RuntimeException("Withdrawal failed: " + ex.getMessage(), ex);
        }
        return mapper.toDto(repository.save(tx));
    }

    private Transaction buildTransaction(TransactionDto dto, TransactionType type) {
        return Transaction.builder()
                .referenceNumber(UUID.randomUUID().toString())
                .senderAccountNumber(dto.getSenderAccountNumber())
                .receiverAccountNumber(dto.getReceiverAccountNumber())
                .amount(dto.getAmount())
                .currencyCode(dto.getCurrencyCode())
                .type(type)
                .status(TransactionStatus.PENDING)
                .description(dto.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
