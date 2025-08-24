package com.example.master.event;

import com.example.master.config.KeycloakUserService;
import com.example.master.services.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DemandEventListener {

    private final EmailService emailService;
    private final KeycloakUserService keycloakUserService;

    public DemandEventListener(EmailService emailService, KeycloakUserService keycloakUserService) {
        this.emailService = emailService;
        this.keycloakUserService = keycloakUserService;
    }

    @KafkaListener(topics = "demand-events", groupId = "demand-service")
    public void consumeEvent(String event) {
        System.out.println("📩 Received Kafka Event: " + event);

        if (event.startsWith("NEW_DEMAND")) {
            // DWCD created → notify FCI & Supplier
            keycloakUserService.getUserEmailsByRole("FCI")
                    .forEach(mail -> emailService.sendEmail(mail, "New Demand Created", "A new demand has been raised."));
            keycloakUserService.getUserEmailsByRole("SUPPLIER")
                    .forEach(mail -> emailService.sendEmail(mail, "New Demand Notification", "A new demand has been raised."));
        } else if (event.startsWith("STATUS_UPDATE")) {
            String[] parts = event.split(":");
            Long demandId = Long.parseLong(parts[1]);
            String status = parts[2];

            switch (status) {
                case "FCI_ACCEPTED" -> {
                    keycloakUserService.getUserEmailsByRole("DWCD")
                            .forEach(mail -> emailService.sendEmail(mail, "FCI Accepted Demand", "Demand " + demandId + " accepted."));
                    keycloakUserService.getUserEmailsByRole("SUPPLIER")
                            .forEach(mail -> emailService.sendEmail(mail, "FCI Accepted Demand", "Demand " + demandId + " accepted by FCI."));
                }
                case "FCI_REJECTED" ->
                        keycloakUserService.getUserEmailsByRole("DWCD")
                                .forEach(mail -> emailService.sendEmail(mail, "FCI Rejected Demand", "Demand " + demandId + " rejected."));
                case "SUPPLIER_ACCEPTED" ->
                        keycloakUserService.getUserEmailsByRole("DWCD")
                                .forEach(mail -> emailService.sendEmail(mail, "Supplier Accepted Demand", "Supplier started manufacturing for " + demandId));
                case "SUPPLIER_REJECTED" ->
                        keycloakUserService.getUserEmailsByRole("DWCD")
                                .forEach(mail -> emailService.sendEmail(mail, "Supplier Rejected Demand", "Supplier rejected demand " + demandId));
                case "CDPO_DISPATCHED" ->
                        keycloakUserService.getUserEmailsByRole("AWC")
                                .forEach(mail -> emailService.sendEmail(mail, "CDPO Dispatched", "CDPO dispatched items for " + demandId));
                case "AWC_ACCEPTED" ->
                        keycloakUserService.getUserEmailsByRole("DWCD")
                                .forEach(mail -> emailService.sendEmail(mail, "AWC Accepted", "AWC distributed items for " + demandId));
            }
        }
    }
}
