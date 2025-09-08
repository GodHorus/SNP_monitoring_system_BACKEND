package com.example.master.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;

@Component
public class DemandEventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public DemandEventHandler(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleDemandEvent(DemandDomainEvent event) {
        System.out.println("📤 Publishing Kafka event after commit: " + event.getPayload());
        kafkaTemplate.send("demand-events", event.getPayload());
        System.out.println("✅ Kafka event published: " + event.getPayload());
    }
}
