package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;


@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBootApplication.class, args);
    }
}