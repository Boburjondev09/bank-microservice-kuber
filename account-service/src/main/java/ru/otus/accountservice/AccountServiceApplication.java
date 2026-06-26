package ru.otus.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static util.ProjectStartingPoint.loggingApplicationParams;

@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
      var own =  SpringApplication.run(AccountServiceApplication.class, args);
        loggingApplicationParams(own.getEnvironment());
    }

}
