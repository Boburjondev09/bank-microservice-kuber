package ru.otus.currencyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static ru.otus.currencyservice.util.ProjectStartingPoint.loggingApplicationParams;


@SpringBootApplication
public class CurrencyServiceApplication {

    public static void main(String[] args) {
      var pro= SpringApplication.run(CurrencyServiceApplication.class, args);
        loggingApplicationParams(pro.getEnvironment());
    }
}