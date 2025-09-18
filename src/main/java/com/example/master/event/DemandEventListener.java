package com.example.master.event;

import com.example.master.Dto.DemandResponseDTO;
import com.example.master.config.KeycloakUserService;
import com.example.master.services.DemandService;
import com.example.master.services.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DemandEventListener {

    private final EmailService emailService;
    private final KeycloakUserService keycloakUserService;
    private final DemandService demandService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DemandEventListener(EmailService emailService,
                               KeycloakUserService keycloakUserService,
                               DemandService demandService) {
        this.emailService = emailService;
        this.keycloakUserService = keycloakUserService;
        this.demandService = demandService;
    }

    @KafkaListener(topics = "demand-events", groupId = "demand-service")
    public void consumeEvent(String eventJson) {
        // Check if the event JSON is null or empty before processing
        if (eventJson == null || eventJson.trim().isEmpty()) {
            System.err.println("⚠️ Skipping empty Kafka message");
            return;  // Exit early if the message is empty or null
        }

        System.out.println("📩 Received Kafka Event JSON: " + eventJson);

        try {
            // Attempt to deserialize the JSON into a Map
            Map<String, String> event = objectMapper.readValue(eventJson, Map.class);

            String type = event.get("type");
            Long demandId = Long.parseLong(event.get("demandId"));

            // Fetch the demand using the demandId
            DemandResponseDTO dto = demandService.getDemandById(demandId).orElse(null);
            if (dto == null) {
                System.err.println("❌ Demand not found for ID " + demandId);
                return;
            }

            // Process the event based on the event type
            switch (type) {
                case "NEW_DEMAND" -> {
                    keycloakUserService.getUserEmailsByRole("FCI")
                            .forEach(mail -> sendRoleBasedMail("FCI", mail, type, dto));
                    keycloakUserService.getUserEmailsByRole("SUPPLIER")
                            .forEach(mail -> sendRoleBasedMail("SUPPLIER", mail, type, dto));
                }
                case "STATUS_UPDATE" -> {
                    String status = event.get("status");
                    handleStatusUpdate(status, dto);
                }
                default -> System.out.println("⚠️ Unknown event type: " + type);
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to process Kafka event: " + eventJson + " | Error: " + e.getMessage());
        }
    }

//    @KafkaListener(topics = "demand-events", groupId = "demand-service")
//    public void consumeEvent(String eventJson) {
//        System.out.println("📩 Received Kafka Event JSON: " + eventJson);
//
//        try {
//            Map<String, String> event = objectMapper.readValue(eventJson, Map.class);
//            String type = event.get("type");
//            Long demandId = Long.parseLong(event.get("demandId"));
//
//            DemandResponseDTO dto = demandService.getDemandById(demandId).orElse(null);
//            if (dto == null) {
//                System.err.println("❌ Demand not found for ID " + demandId);
//                return;
//            }
//
//            switch (type) {
//                case "NEW_DEMAND" -> {
//                    keycloakUserService.getUserEmailsByRole("FCI")
//                            .forEach(mail -> sendRoleBasedMail("FCI", mail, type, dto));
//                    keycloakUserService.getUserEmailsByRole("SUPPLIER")
//                            .forEach(mail -> sendRoleBasedMail("SUPPLIER", mail, type, dto));
//                }
//                case "STATUS_UPDATE" -> {
//                    String status = event.get("status");
//                    handleStatusUpdate(status, dto);
//                }
//                default -> System.out.println("⚠️ Unknown event type: " + type);
//            }
//        } catch (Exception e) {
//            System.err.println("❌ Failed to process Kafka event: " + e.getMessage());
//        }
//    }

    private void handleStatusUpdate(String status, DemandResponseDTO dto) {
        switch (status) {
            case "FCI_ACCEPTED" -> {
                keycloakUserService.getUserEmailsByRole("DWCD")
                        .forEach(mail -> sendRoleBasedMail("DWCD", mail, status, dto));
                keycloakUserService.getUserEmailsByRole("SUPPLIER")
                        .forEach(mail -> sendRoleBasedMail("SUPPLIER", mail, status, dto));
            }
            case "FCI_REJECTED" -> keycloakUserService.getUserEmailsByRole("DWCD")
                    .forEach(mail -> sendRoleBasedMail("DWCD", mail, status, dto));
            case "SUPPLIER_ACCEPTED" -> keycloakUserService.getUserEmailsByRole("DWCD")
                    .forEach(mail -> sendRoleBasedMail("DWCD", mail, status, dto));
            case "SUPPLIER_REJECTED" -> keycloakUserService.getUserEmailsByRole("DWCD")
                    .forEach(mail -> sendRoleBasedMail("DWCD", mail, status, dto));
            case "CDPO_DISPATCHED" -> keycloakUserService.getUserEmailsByRole("AWC")
                    .forEach(mail -> sendRoleBasedMail("AWC", mail, status, dto));
            case "AWC_ACCEPTED" -> keycloakUserService.getUserEmailsByRole("DWCD")
                    .forEach(mail -> sendRoleBasedMail("DWCD", mail, status, dto));
        }
    }

    private void sendRoleBasedMail(String role, String to, String eventType, DemandResponseDTO dto) {
        String subject = "";
        String body = "";

        switch (role) {
            case "SUPPLIER" -> {
                subject = "Request for Supply of " + dto.getProductQuantity().getProductType() + " for ICDS Projects";
                body = String.format("""
                                Dear %s,
                                
                                We request the supply of %s under the Supplementary Nutrition Programme (SNP)
                                for beneficiaries: %s in %s district(s).
                                
                                Demand Details:
                                • Product: %s
                                • Duration: %s to %s (%d days)
                                • Quantity Required (per CDPO):
                                  %s
                                • Notes: %s
                                
                                Regards,
                                DWCD Office
                                """,
                        dto.getSupplier() != null ? dto.getSupplier().getName() : "Supplier",
                        dto.getProductQuantity().getProductType(),
                        dto.getBeneficiary().getBeneficiaryName(),
                        dto.getCdpoDetails().stream()
                                .map(cdpo -> cdpo.getDistrictName())
                                .distinct() // avoid duplicates
                                .collect(Collectors.joining(", ")),   // 🔹 all persisted districts
                        dto.getProductQuantity().getProductType(),
                        dto.getFromDate(), dto.getToDate(), dto.getTotalDays(),
                        dto.getCdpoDetails().stream()
                                .map(cdpo -> cdpo.getCdpoName() + ": " + cdpo.getQuantity() + " " + cdpo.getQuantityUnits()
                                        + " (" + cdpo.getDistrictName() + ")") // 🔹 show district per CDPO
                                .reduce((a, b) -> a + "\n  " + b).orElse("N/A"),
                        dto.getNotes()
                );
            }
            case "FCI" -> {
                subject = "Request for Supply of Fortified Rice for SNP Production";
                body = String.format("""
                                Dear Sir/Madam,
                                
                                Please provide fortified rice for SNP demand (ID: %d) for beneficiaries: %s.
                                
                                Manufacturer: %s
                                District(s): %s
                                
                                Commodity Requirement:
                                %s
                                
                                Regards,
                                DWCD Office
                                """,
                        dto.getId(),
                        dto.getBeneficiary().getBeneficiaryName(),
                        dto.getSupplier().getName(),
                        dto.getCdpoDetails().stream()
                                .map(cdpo -> cdpo.getDistrictName())
                                .distinct()
                                .collect(Collectors.joining(", ")),
                        dto.getProductQuantity().getCommodityQuantities().entrySet().stream()
                                .map(e -> e.getKey() + " → " + e.getValue() + " Kg")
                                .reduce((a, b) -> a + "\n" + b).orElse("N/A")
                );
            }

            case "DWCD", "CDPO", "DSWO", "DC" -> {
                subject = "Intimation of SNP Supply Initiation - Demand " + dto.getId();
                body = String.format("""
                                Dear Sir/Madam,
                                
                                SNP supply has been initiated for beneficiaries: %s.
                                
                                Supply Details:
                                • Total beneficiaries (from CDPOs): %d
                                • Feeding Days: %d
                                • Product: %s
                                • Supplier: %s
                                • District(s): %s
                                
                                Kindly ensure monitoring and smooth implementation.
                                
                                Regards,
                                DWCD Office
                                """,
                        dto.getBeneficiary().getBeneficiaryName(),
                        dto.getCdpoDetails().stream().mapToInt(c -> c.getBeneficiaryCount()).sum(),
                        dto.getTotalDays(),
                        dto.getProductQuantity().getProductType(),
                        dto.getSupplier().getName(),
                        dto.getCdpoDetails().stream()
                                .map(cdpo -> cdpo.getDistrictName())
                                .distinct()
                                .collect(Collectors.joining(", "))
                );
            }

            case "AWC" -> {
                subject = "CDPO Dispatched Items for SNP Distribution - Demand " + dto.getId();
                body = String.format("""
                                Dear Anganwadi Worker,
                                
                                CDPO has dispatched items for demand %d (%s).
                                Product: %s
                                Beneficiaries: %s
                                
                                Please ensure timely distribution.
                                
                                Regards,
                                CDPO/DWCD Office
                                """,
                        dto.getId(),
                        dto.getDescription(),
                        dto.getProductQuantity().getProductType(),
                        dto.getBeneficiary().getBeneficiaryName()
                );
            }
        }

        emailService.sendEmail(to, subject, body);
    }
}
