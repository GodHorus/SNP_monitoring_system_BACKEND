package com.example.master.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;

import java.util.Map;

@Component
public class DemandEventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DemandEventHandler(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onDomainEvent(DemandDomainEvent event) {
        try {
            // Ensure valid JSON
            String eventJson = objectMapper.writeValueAsString(Map.of(
                    "type", "NEW_DEMAND",
                    "demandId", event.getPayload()
            ));

            System.out.println("📤 Publishing Kafka event: " + eventJson);
            kafkaTemplate.send("demand-events", eventJson);
        } catch (Exception e) {
            System.err.println("❌ Failed to send event to Kafka: " + e.getMessage());
        }
    }
}

//@Component
//public class DemandEventHandler {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    public DemandEventHandler(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    @KafkaListener(topics = "demand-events", groupId = "demand-service")
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    public void handleDemandEvent(DemandDomainEvent event) {
//        System.out.println("📤 Publishing Kafka event after commit: " + event.getPayload());
//        kafkaTemplate.send("demand-events", event.getPayload());
//        System.out.println("✅ Kafka event published: " + event.getPayload());
//    }
//}
