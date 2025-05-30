package com.example.awc_consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@Slf4j
public class AwcConsumerApplication {

    public static void main(String[] args) {
        log.info("✈️  Starting AWC Consumer Application...");
        SpringApplication.run(AwcConsumerApplication.class, args);
        log.info("✅ AWC Consumer Application started successfully!");
    }

}
