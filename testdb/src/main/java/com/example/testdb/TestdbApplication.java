package com.example.testdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestdbApplication.class, args);
    }

}
