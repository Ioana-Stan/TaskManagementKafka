package com.example.taskmanagementkafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TaskEventConsumer {

    private final List<TaskEvent> events = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = "${kafka.topic}", groupId = "task-events-group")
    public void listen(String message) throws JsonProcessingException {
        TaskEvent event = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .readValue(message, TaskEvent.class);
        events.add(event);
    }

    public List<TaskEvent> getAllEvents() {
        return events;
    }
}

