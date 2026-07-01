package ru.otus.accountservice.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.accountservice.aggregate.DTO.AccountDto;
import ru.otus.accountservice.service.business.AccountInterface;

import java.math.BigDecimal;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: account-service
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountEndpoint {

    private final AccountInterface accountInterface;
    private static final Logger LOG = LoggerFactory.getLogger(AccountEndpoint.class);

    public AccountEndpoint(AccountInterface accountInterface) {
        this.accountInterface = accountInterface;
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(accountInterface.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        LOG.info("GET /api/accounts/get/{}", id);
        return ResponseEntity.ok(accountInterface.findById(id));
    }

    @GetMapping("/get/number/{accountNumber}")
    public ResponseEntity<AccountDto> getAccountByNumber(@PathVariable String accountNumber) {
        LOG.info("GET /api/accounts/get/number/{}", accountNumber);
        return ResponseEntity.ok(accountInterface.findByAccountNumber(accountNumber));
    }

    @PostMapping("/save")
    public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto dto) {
        LOG.info("POST /api/accounts/save : {}", dto);
        return ResponseEntity.ok(accountInterface.save(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id,
                                                    @RequestBody AccountDto dto) {
        LOG.info("PUT /api/accounts/update/{}", id);
        return ResponseEntity.ok(accountInterface.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        LOG.info("DELETE /api/accounts/delete/{}", id);
        accountInterface.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<AccountDto> deposit(@RequestParam String accountNumber,
                                              @RequestParam BigDecimal amount) {
        LOG.info("POST /api/accounts/deposit : accountNumber={}, amount={}", accountNumber, amount);
        return ResponseEntity.ok(accountInterface.deposit(accountNumber, amount));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<AccountDto> withdraw(@RequestParam String accountNumber,
                                               @RequestParam BigDecimal amount) {
        LOG.info("POST /api/accounts/withdraw : accountNumber={}, amount={}", accountNumber, amount);
        return ResponseEntity.ok(accountInterface.withdraw(accountNumber, amount));
    }
}
