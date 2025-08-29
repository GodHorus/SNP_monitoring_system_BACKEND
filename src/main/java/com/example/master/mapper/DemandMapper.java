//package com.example.master.mapper;
//
//import com.example.master.Dto.*;
//import com.example.master.model.Demand;
//import com.example.master.model.DemandAwcDetail;
//import java.util.stream.Collectors;
//
//public class DemandMapper {
//
//    public static DemandResponseDTO toResponseDTO(Demand demand) {
//        DemandResponseDTO dto = new DemandResponseDTO();
//
//        dto.setId(demand.getId());
//        dto.setDescription(demand.getDescription());
//        dto.setStatus(demand.getStatus());
//
//        dto.setFromDate(demand.getFromDate());
//        dto.setToDate(demand.getToDate());
//
//        dto.setFciId(demand.getFciId());
//        dto.setFciDocs(demand.getFciDocs());
//
//        dto.setQuantity(demand.getQuantity());
//        dto.setQuantityUnit(demand.getQuantityUnit());
//
//        dto.setSupplierId(demand.getSupplierId());
//        dto.setSupplierDocs(demand.getSupplierDocs());
//
//        dto.setDistrict(demand.getDistrict() != null ? new DistrictDTO(demand.getDistrict()) : null);
//        dto.setCdpo(demand.getCdpo() != null ? new CdpoDTO(demand.getCdpo()) : null);
//        dto.setSupervisor(demand.getSupervisor() != null ? new SupervisorDTO(demand.getSupervisor()) : null);
//
//        // Map AWC details
//        if (demand.getAwcDetails() != null) {
//            dto.setAwcDetails(
//                    demand.getAwcDetails().stream()
//                            .map(DemandAwcDetailDTO::new)
//                            .collect(Collectors.toList())
//            );
//        }
//
//        dto.setNotes(demand.getNotes());
//        dto.setCreatedAt(demand.getCreatedAt());
//        dto.setUpdatedAt(demand.getUpdatedAt());
//
//        dto.setFciAcceptedAt(demand.getFciAcceptedAt());
//        dto.setFciRejectedAt(demand.getFciRejectedAt());
//        dto.setSupplierAcceptedAt(demand.getSupplierAcceptedAt());
//        dto.setSupplierRejectedAt(demand.getSupplierRejectedAt());
//        dto.setCdpoDispatchedAt(demand.getCdpoDispatchedAt());
//        dto.setAwcAcceptedAt(demand.getAwcAcceptedAt());
//        dto.setRejectionReason(demand.getRejectionReason());
//
//        return dto;
//    }
//}
