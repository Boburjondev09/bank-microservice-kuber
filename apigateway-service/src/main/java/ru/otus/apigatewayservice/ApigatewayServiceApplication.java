package ru.otus.apigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static ru.otus.apigatewayservice.util.ProjectStartingPoint.loggingApplicationParams;

@SpringBootApplication
public class ApigatewayServiceApplication {

    public static void main(String[] args) {
      var project =  SpringApplication.run(ApigatewayServiceApplication.class, args);
        loggingApplicationParams(project.getEnvironment());
    }
}