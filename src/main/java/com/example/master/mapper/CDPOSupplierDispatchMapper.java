//package com.example.master.mapper;
//
//import com.example.master.Dto.CDPOSupplierDispatchDTO;
//import com.example.master.model.AcceptDemand;
//import com.example.master.model.CDPOSupplierDispatch;
//import com.example.master.model.Sector;
//
//public class CDPOSupplierDispatchMapper {
//
//    public static CDPOSupplierDispatchDTO toDTO(CDPOSupplierDispatch entity) {
//        CDPOSupplierDispatchDTO dto = new CDPOSupplierDispatchDTO();
//        dto.setId(entity.getId());
//        dto.setDispatchPackets(entity.getDispatchPackets());
//        dto.setRemarks(entity.getRemarks());
//        dto.setAcceptDemandId(entity.getAcceptDemand().getId());
//        dto.setSublotNo(entity.getSublotNo());
//
//        if (entity.getSector() != null) {
//            dto.setSectorId(entity.getSector().getId());
//            dto.setSectorName(entity.getSector().getName());
//            dto.setSectorStatus(entity.getSector().getStatus());
//        }
//        return dto;
//    }
//
//    public static CDPOSupplierDispatch toEntity(CDPOSupplierDispatchDTO dto, AcceptDemand acceptDemand, Sector sector) {
//        CDPOSupplierDispatch entity = new CDPOSupplierDispatch();
//        entity.setId(dto.getId());
//        entity.setDispatchPackets(dto.getDispatchPackets());
//        entity.setRemarks(dto.getRemarks());
//        entity.setAcceptDemand(acceptDemand);
//        entity.setSublotNo(dto.getSublotNo());
//        entity.setSector(sector);
//        return entity;
//    }
//}
