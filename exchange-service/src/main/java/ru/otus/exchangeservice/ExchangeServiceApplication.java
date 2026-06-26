package ru.otus.exchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static ru.otus.exchangeservice.util.ProjectStartingPoint.loggingApplicationParams;

@SpringBootApplication
public class ExchangeServiceApplication {

    public static void main(String[] args) {
       var target= SpringApplication.run(ExchangeServiceApplication.class, args);
        loggingApplicationParams(target.getEnvironment());
    }
}