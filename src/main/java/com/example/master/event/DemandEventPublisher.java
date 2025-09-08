package com.example.master.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;



@Component
public class DemandEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public DemandEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publish(String event) {
        System.out.println("📢 Publishing domain event: " + event);
        eventPublisher.publishEvent(new DemandDomainEvent(event));
    }
}



//@Component
//public class DemandEventPublisher {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    public DemandEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    @KafkaListener(topics = "demand-events", groupId = "demand-service")
//    public void publish(String event) {
//        kafkaTemplate.send("demand-events", event);
//        System.out.println("📤 Published Kafka event: " + event);
//    }
//}
