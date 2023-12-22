package com.example.tjckafkademo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class TjcKafkaDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(TjcKafkaDemo1Application.class, args);
    }

}
