package com.example.master.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class DemandEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public DemandEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String event) {
        kafkaTemplate.send("demand-events", event);
        System.out.println("📤 Published Kafka event: " + event);
    }
}
