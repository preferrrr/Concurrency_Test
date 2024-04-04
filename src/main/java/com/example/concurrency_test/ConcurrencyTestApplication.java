package com.example.concurrency_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ConcurrencyTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyTestApplication.class, args);
    }

}
