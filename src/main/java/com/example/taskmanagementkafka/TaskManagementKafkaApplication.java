package com.example.taskmanagementkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
@SpringBootApplication
@EnableKafka
public class TaskManagementKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagementKafkaApplication.class, args);
    }
}
