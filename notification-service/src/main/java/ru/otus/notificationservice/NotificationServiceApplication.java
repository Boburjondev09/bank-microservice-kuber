package ru.otus.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static ru.otus.notificationservice.util.ProjectStartingPoint.loggingApplicationParams;

@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
       var adjust = SpringApplication.run(NotificationServiceApplication.class, args);
        loggingApplicationParams(adjust.getEnvironment());
    }
}