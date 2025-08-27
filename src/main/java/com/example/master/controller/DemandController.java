package com.example.master.controller;

import com.example.master.Dto.DemandDTO;
import com.example.master.Dto.DemandResponseDTO;
import com.example.master.model.Demand;
import com.example.master.services.DemandService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.master.Dto.DecisionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/demands")
//@CrossOrigin(origins = "*")
public class DemandController {

    private static final Logger logger = LoggerFactory.getLogger(DemandController.class);
    private final DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    // DWCD creates demand
    @PreAuthorize("hasAnyRole('ADMIN','DWCD')")
    @PostMapping
    public ResponseEntity<Demand> createDemand(@RequestBody DemandDTO dto) {
        logCurrentUserAuthorities("createDemand");
        Demand demand = demandService.createDemand(dto);
        return ResponseEntity.ok(demand);
    }

    // Admin & DWCD can view all demands
//    @PreAuthorize("hasAnyRole('ADMIN','DWCD')")
//    @GetMapping
//    public ResponseEntity<List<Demand>> getAllDemands() {
//        logCurrentUserAuthorities("getAllDemands");
//        List<Demand> demands = demandService.getAllDemands();

    /// /        List<DemandDTO> demandDTOs = demands.stream()
    /// ///                .map(demand -> new DemandDTO(demand)) // Convert Demand entity to DTO
    /// /                .collect(Collectors.toList());
//        return ResponseEntity.ok(demands);
//    }
    @PreAuthorize("hasAnyRole('ADMIN','DWCD')")
    @GetMapping
    public ResponseEntity<List<DemandResponseDTO>> getAllDemands() {
        return ResponseEntity.ok(demandService.getAllDemands());
    }

    // All authenticated roles can view a specific demand
    @PreAuthorize("hasAnyRole('ADMIN','DWCD','FCI','SUPPLIER','CDPO','AWC')")
    @GetMapping("/{id}")
    public ResponseEntity<Demand> getDemandById(@PathVariable Long id) {
        logCurrentUserAuthorities("getDemandById");
        Demand demand = demandService.getDemandById(id);
        return ResponseEntity.ok(demand);
    }

    // *** CRITICAL: Admin only - delete demand with enhanced security ***
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteDemand(@PathVariable Long id) {
        // Enhanced logging and security check
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("=== DELETE DEMAND REQUEST ===");
        logger.info("Demand ID: {}", id);
        logger.info("User: {}", auth != null ? auth.getName() : "UNKNOWN");

        if (auth != null) {
            List<String> authorities = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            logger.info("User authorities: {}", authorities);

            // Double-check admin role manually
            boolean hasAdminRole = authorities.contains("ROLE_ADMIN");
            logger.info("Has ADMIN role: {}", hasAdminRole);

            if (!hasAdminRole) {
                logger.error("SECURITY VIOLATION: Non-admin user {} attempted to delete demand {}",
                        auth.getName(), id);
                return ResponseEntity.status(403).body(Map.of(
                        "error", "Access Denied",
                        "message", "Only ADMIN users can delete demands"
                ));
            }
        } else {
            logger.error("SECURITY VIOLATION: Unauthenticated request to delete demand {}", id);
            return ResponseEntity.status(401).body(Map.of(
                    "error", "Unauthorized",
                    "message", "Authentication required"
            ));
        }

        try {
            demandService.deleteDemand(id);
            logger.info("Demand {} successfully deleted by admin user {}", id, auth.getName());
            return ResponseEntity.ok(Map.of(
                    "message", "Demand deleted successfully",
                    "deletedId", id.toString()
            ));
        } catch (Exception e) {
            logger.error("Error deleting demand {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Deletion Failed",
                    "message", e.getMessage()
            ));
        }
    }

    // FCI-specific endpoints
    @PreAuthorize("hasRole('FCI')")
    @GetMapping("/pending")
    public ResponseEntity<List<DemandResponseDTO>> getPendingDemands() {
        logCurrentUserAuthorities("getPendingDemands");
        List<DemandResponseDTO> demands = demandService.getPendingDemandsForFCI();
        return ResponseEntity.ok(demands);
    }

    @PreAuthorize("hasRole('FCI')")
    @PostMapping("/{id}/fci-decision")
    public ResponseEntity<Map<String, String>> fciDecision(@PathVariable Long id, @RequestBody DecisionRequest request) {
        logCurrentUserAuthorities("fciDecision");
        String status = request.isAccept() ? "FCI_ACCEPTED" : "FCI_REJECTED";
        demandService.updateStatus(id, status);

        String message = request.isAccept() ? "Demand accepted by FCI" : "Demand rejected by FCI";
        return ResponseEntity.ok(Map.of("message", message, "status", status));
    }

    // Supplier-specific endpoints
//    @PreAuthorize("hasRole('SUPPLIER')")
//    @GetMapping("/fci-accepted")
//    public ResponseEntity<List<DemandResponseDTO>> getAcceptedDemands() {
//        logCurrentUserAuthorities("getAcceptedDemands");
//        List<DemandResponseDTO> demands = demandService.getAcceptedDemandsForSupplier();
//        return ResponseEntity.ok(demands);
//    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @GetMapping("/fci-accepted")
    public ResponseEntity<List<DemandResponseDTO>> getAcceptedDemands() {
        logCurrentUserAuthorities("getAcceptedDemands");
        return ResponseEntity.ok(demandService.getAcceptedDemandsForSupplier());
    }

    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/{id}/supplier-decision")
    public ResponseEntity<Map<String, String>> supplierDecision(@PathVariable Long id, @RequestBody DecisionRequest request) {
        logCurrentUserAuthorities("supplierDecision");
        String status = request.isAccept() ? "SUPPLIER_ACCEPTED" : "SUPPLIER_REJECTED";
        demandService.updateStatus(id, status);

        String message = request.isAccept() ? "Supplier accepted and started manufacturing" : "Supplier rejected the demand";
        return ResponseEntity.ok(Map.of("message", message, "status", status));
    }

    // CDPO-specific endpoints
//    @PreAuthorize("hasRole('CDPO')")
//    @GetMapping("/manufactured")
//    public ResponseEntity<List<DemandResponseDTO>> getManufacturedDemands() {
//        logCurrentUserAuthorities("getManufacturedDemands");
//        List<DemandResponseDTO> demands = demandService.getManufacturedDemandsForCDPO();
//        return ResponseEntity.ok(demands);
//    }
    @PreAuthorize("hasRole('CDPO')")
    @GetMapping("/manufactured")
    public ResponseEntity<List<DemandResponseDTO>> getManufacturedDemands() {
        logCurrentUserAuthorities("getManufacturedDemands");
        return ResponseEntity.ok(demandService.getManufacturedDemandsForCDPO());
    }


    @PreAuthorize("hasRole('CDPO')")
    @PostMapping("/{id}/cdpo-dispatch")
    public ResponseEntity<Map<String, String>> cdpoDispatch(@PathVariable Long id) {
        logCurrentUserAuthorities("cdpoDispatch");
        demandService.updateStatus(id, "CDPO_DISPATCHED");
        return ResponseEntity.ok(Map.of("message", "CDPO dispatched to AWC", "status", "CDPO_DISPATCHED"));
    }

    // AWC-specific endpoints
//    @PreAuthorize("hasRole('AWC')")
//    @GetMapping("/dispatched")
//    public ResponseEntity<List<DemandResponseDTO>> getDispatchedDemands() {
//        logCurrentUserAuthorities("getDispatchedDemands");
//        List<DemandResponseDTO> demands = demandService.getDispatchedDemandsForAWC();
//        return ResponseEntity.ok(demands);
//    }

    @PreAuthorize("hasRole('AWC')")
    @GetMapping("/dispatched")
    public ResponseEntity<List<DemandResponseDTO>> getDispatchedDemands() {
        logCurrentUserAuthorities("getDispatchedDemands");
        return ResponseEntity.ok(demandService.getDispatchedDemandsForAWC());
    }

    @PreAuthorize("hasRole('AWC')")
    @PostMapping("/{id}/awc-accept")
    public ResponseEntity<Map<String, String>> awcAccept(@PathVariable Long id) {
        logCurrentUserAuthorities("awcAccept");
        demandService.updateStatus(id, "AWC_ACCEPTED");
        return ResponseEntity.ok(Map.of("message", "AWC accepted and distributed to beneficiaries", "status", "AWC_ACCEPTED"));
    }

    // Dashboard endpoints for different roles
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/dashboard")
    public ResponseEntity<Map<String, Object>> getAdminDashboard() {
        logCurrentUserAuthorities("getAdminDashboard");
        List<DemandResponseDTO> allDemands = demandService.getAllDemands();

        long pending = allDemands.stream().filter(d -> "PENDING".equals(d.getStatus())).count();
        long fciAccepted = allDemands.stream().filter(d -> "FCI_ACCEPTED".equals(d.getStatus())).count();
        long supplierAccepted = allDemands.stream().filter(d -> "SUPPLIER_ACCEPTED".equals(d.getStatus())).count();
        long dispatched = allDemands.stream().filter(d -> "CDPO_DISPATCHED".equals(d.getStatus())).count();
        long completed = allDemands.stream().filter(d -> "AWC_ACCEPTED".equals(d.getStatus())).count();
        long rejected = allDemands.stream().filter(d -> d.getStatus().endsWith("_REJECTED")).count();

        Map<String, Object> dashboard = Map.of(
                "total", allDemands.size(),
                "pending", pending,
                "fci_accepted", fciAccepted,
                "supplier_accepted", supplierAccepted,
                "dispatched", dispatched,
                "completed", completed,
                "rejected", rejected,
                "demands", allDemands
        );

        return ResponseEntity.ok(dashboard);
    }

    // Status tracking endpoint
    @PreAuthorize("hasAnyRole('ADMIN','DWCD','FCI','SUPPLIER','CDPO','AWC')")
    @GetMapping("/{id}/status-history")
    public ResponseEntity<Map<String, Object>> getStatusHistory(@PathVariable Long id) {
        logCurrentUserAuthorities("getStatusHistory");
        Demand demand = demandService.getDemandById(id);

        Map<String, Object> history = Map.of(
                "id", demand.getId(),
                "currentStatus", demand.getStatus(),
                "createdAt", demand.getCreatedAt(),
                "fciAcceptedAt", demand.getFciAcceptedAt(),
                "fciRejectedAt", demand.getFciRejectedAt(),
                "supplierAcceptedAt", demand.getSupplierAcceptedAt(),
                "supplierRejectedAt", demand.getSupplierRejectedAt(),
                "cdpoDispatchedAt", demand.getCdpoDispatchedAt(),
                "awcAcceptedAt", demand.getAwcAcceptedAt()
        );

        return ResponseEntity.ok(history);
    }

    /**
     * Helper method to log current user's authorities for debugging
     */
    private void logCurrentUserAuthorities(String operation) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            List<String> authorities = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            logger.info("Operation: {} | User: {} | Authorities: {}",
                    operation, auth.getName(), authorities);
        } else {
            logger.warn("Operation: {} | No authentication found", operation);
        }
    }
}