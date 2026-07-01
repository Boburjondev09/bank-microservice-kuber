package ru.otus.transactionservice.aggregate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.transactionservice.aggregate.client.dto.AccountClientDto;

import java.math.BigDecimal;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: transaction-service
 * @description Feign client to account-service resolved via Eureka
 */
@FeignClient(name = "account-service")
public interface AccountServiceClient {

    @GetMapping("/api/accounts/get/number/{accountNumber}")
    AccountClientDto getAccountByNumber(@PathVariable("accountNumber") String accountNumber);

    @PostMapping("/api/accounts/withdraw")
    AccountClientDto withdraw(@RequestParam("accountNumber") String accountNumber,
                               @RequestParam("amount") BigDecimal amount);

    @PostMapping("/api/accounts/deposit")
    AccountClientDto deposit(@RequestParam("accountNumber") String accountNumber,
                              @RequestParam("amount") BigDecimal amount);
}
