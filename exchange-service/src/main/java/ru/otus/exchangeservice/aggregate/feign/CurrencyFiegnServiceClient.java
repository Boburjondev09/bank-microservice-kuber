package ru.otus.exchangeservice.aggregate.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.exchangeservice.aggregate.DTO.base.CurrencyRateClientDto;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: exchange-service
 * @description Feign client to currency-service, resolved via Eureka service discovery
 */
@FeignClient(name = "currency-service")
public interface CurrencyFiegnServiceClient {

    @GetMapping("/api/rate/exchange/get")
    CurrencyRateClientDto getRate(@RequestParam("from") String from, @RequestParam("to") String to);
}
