package com.example.taskmanagementkafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks/events")
public class TaskEventController {

    private final TaskEventProducer producer;
    private final TaskEventConsumer consumer;

    public TaskEventController(TaskEventProducer producer, TaskEventConsumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @PostMapping
    public ResponseEntity<String> publishEvent(
            @RequestParam String taskId,
            @RequestParam String eventType,
            @RequestParam LocalDateTime timestamp
    ) {
        TaskEvent event = new TaskEvent(taskId, eventType, timestamp);

        try {
            producer.publish(event);
            return ResponseEntity.ok("Event published to Kafka");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to publish event");
        }
    }


    @GetMapping
    public List<TaskEvent> getAllEvents() {
        return consumer.getAllEvents();
    }
}
