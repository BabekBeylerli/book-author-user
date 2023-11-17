package com.example.ingress_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class IngressTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngressTaskApplication.class, args);
    }

}
