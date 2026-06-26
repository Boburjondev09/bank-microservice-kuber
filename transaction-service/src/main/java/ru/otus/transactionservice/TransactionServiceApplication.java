package ru.otus.transactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static ru.otus.transactionservice.util.ProjectStartingPoint.loggingApplicationParams;

@SpringBootApplication
public class TransactionServiceApplication {

    public static void main(String[] args) {
      var project =  SpringApplication.run(TransactionServiceApplication.class, args);
        loggingApplicationParams(project.getEnvironment());
    }
}