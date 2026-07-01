package ru.otus.transactionservice.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.transactionservice.aggregate.DTO.TransactionDto;
import ru.otus.transactionservice.service.business.TransactionInterface;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: transaction-service
 */
@RestController
@RequestMapping("/api/transactions")
public class TransactionEndpoint {

    private final TransactionInterface transactionInterface;
    private static final Logger LOG = LoggerFactory.getLogger(TransactionEndpoint.class);

    public TransactionEndpoint(TransactionInterface transactionInterface) {
        this.transactionInterface = transactionInterface;
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(transactionInterface.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TransactionDto> getById(@PathVariable Long id) {
        LOG.info("GET /api/transactions/get/{}", id);
        return ResponseEntity.ok(transactionInterface.findById(id));
    }

    @GetMapping("/get/ref/{referenceNumber}")
    public ResponseEntity<TransactionDto> getByRef(@PathVariable String referenceNumber) {
        LOG.info("GET /api/transactions/get/ref/{}", referenceNumber);
        return ResponseEntity.ok(transactionInterface.findByReferenceNumber(referenceNumber));
    }

    @GetMapping("/get/sender/{accountNumber}")
    public ResponseEntity<?> getBySender(@PathVariable String accountNumber) {
        LOG.info("GET /api/transactions/get/sender/{}", accountNumber);
        return ResponseEntity.ok(transactionInterface.findBySender(accountNumber));
    }

    @GetMapping("/get/receiver/{accountNumber}")
    public ResponseEntity<?> getByReceiver(@PathVariable String accountNumber) {
        LOG.info("GET /api/transactions/get/receiver/{}", accountNumber);
        return ResponseEntity.ok(transactionInterface.findByReceiver(accountNumber));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDto> transfer(@RequestBody TransactionDto dto) {
        LOG.info("POST /api/transactions/transfer : {}", dto);
        return ResponseEntity.ok(transactionInterface.transfer(dto));
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> deposit(@RequestBody TransactionDto dto) {
        LOG.info("POST /api/transactions/deposit : {}", dto);
        return ResponseEntity.ok(transactionInterface.deposit(dto));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<TransactionDto> withdrawal(@RequestBody TransactionDto dto) {
        LOG.info("POST /api/transactions/withdrawal : {}", dto);
        return ResponseEntity.ok(transactionInterface.withdrawal(dto));
    }
}
