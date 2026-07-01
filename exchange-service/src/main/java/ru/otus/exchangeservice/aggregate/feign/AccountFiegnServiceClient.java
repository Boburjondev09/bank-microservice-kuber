package ru.otus.exchangeservice.aggregate.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.exchangeservice.aggregate.DTO.base.AccountClientDto;

import java.math.BigDecimal;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: exchange-service
 * @description Feign client to account-service, resolved via Eureka service discovery
 */
@FeignClient(name = "account-service")
public interface AccountFiegnServiceClient {

    @GetMapping("/api/accounts/get/number/{accountNumber}")
    AccountClientDto getAccountByNumber(@PathVariable("accountNumber") String accountNumber);

    @PostMapping("/api/accounts/withdraw")
    AccountClientDto withdraw(@RequestParam("accountNumber") String accountNumber,
                               @RequestParam("amount") BigDecimal amount);

    @PostMapping("/api/accounts/deposit")
    AccountClientDto deposit(@RequestParam("accountNumber") String accountNumber,
                              @RequestParam("amount") BigDecimal amount);
}
