package ru.otus.exchangeservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.exchangeservice.aggregate.DTO.ConvertRequestDto;
import ru.otus.exchangeservice.aggregate.DTO.ExchangeOperationDto;
import ru.otus.exchangeservice.aggregate.feign.AccountFiegnServiceClient;
import ru.otus.exchangeservice.aggregate.feign.CurrencyFiegnServiceClient;
import ru.otus.exchangeservice.aggregate.DTO.base.CurrencyRateClientDto;
import ru.otus.exchangeservice.aggregate.entity.ExchangeOperation;
import ru.otus.exchangeservice.aggregate.entity.ExchangeStatus;
import ru.otus.exchangeservice.aggregate.mapper.ExchangeOperationMapper;
import ru.otus.exchangeservice.repository.ExchangeOperationRepository;
import ru.otus.exchangeservice.service.business.ExchangeInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: exchange-service
 * @description orchestrates currency-service (rate lookup) and account-service
 * (withdraw/deposit) to perform an end-to-end FX conversion for an account
 */
@Service
public class ExchangeService implements ExchangeInterface {

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeService.class);

    private final ExchangeOperationRepository repository;
    private final ExchangeOperationMapper mapper;
    private final CurrencyFiegnServiceClient currencyFiegnServiceClient;
    private final AccountFiegnServiceClient accountFiegnServiceClient;

    public ExchangeService(ExchangeOperationRepository repository,
                            ExchangeOperationMapper mapper,
                            CurrencyFiegnServiceClient currencyFiegnServiceClient,
                            AccountFiegnServiceClient accountFiegnServiceClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.currencyFiegnServiceClient = currencyFiegnServiceClient;
        this.accountFiegnServiceClient = accountFiegnServiceClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExchangeOperationDto> findAll() {
        LOG.info("Finding all exchange operations");
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ExchangeOperationDto findById(Long id) {
        LOG.info("REQUEST: (findById) : {}", id);
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Exchange operation not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExchangeOperationDto> findByAccountNumber(String accountNumber) {
        LOG.info("REQUEST: (findByAccountNumber) : {}", accountNumber);
        return repository.findByAccountNumber(accountNumber)
                .stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional
    public ExchangeOperationDto convert(ConvertRequestDto request) {
        LOG.info("REQUEST: (convert) : {}", request);

        // 1. Get current rate from currency-service
        CurrencyRateClientDto rateDto = currencyFiegnServiceClient.getRate(
                request.getFromCurrency(), request.getToCurrency());

        BigDecimal rate = rateDto.getRate();
        BigDecimal convertedAmount = request.getAmount()
                .multiply(rate)
                .setScale(2, RoundingMode.HALF_UP);

        ExchangeOperation operation = ExchangeOperation.builder()
                .accountNumber(request.getAccountNumber())
                .fromCurrency(request.getFromCurrency())
                .toCurrency(request.getToCurrency())
                .sourceAmount(request.getAmount())
                .convertedAmount(convertedAmount)
                .appliedRate(rate)
                .createdAt(LocalDateTime.now())
                .status(ExchangeStatus.PENDING)
                .build();

        try {
            // 2. Withdraw source amount from account-service
            accountFiegnServiceClient.withdraw(request.getAccountNumber(), request.getAmount());

            // 3. Deposit converted amount back to the same account
            accountFiegnServiceClient.deposit(request.getAccountNumber(), convertedAmount);

            operation.setStatus(ExchangeStatus.COMPLETED);
        } catch (Exception ex) {
            LOG.error("Exchange failed for account {}: {}", request.getAccountNumber(), ex.getMessage());
            operation.setStatus(ExchangeStatus.FAILED);
            repository.save(operation);
            throw new RuntimeException("Exchange operation failed: " + ex.getMessage(), ex);
        }

        ExchangeOperation saved = repository.save(operation);
        return mapper.toDto(saved);
    }
}
