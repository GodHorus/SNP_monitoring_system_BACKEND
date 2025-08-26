package com.example.master.services.impl;

import com.example.master.Dto.DemandDTO;
import com.example.master.event.DemandEventPublisher;
import com.example.master.exception.NotFoundException;
import com.example.master.model.*;
import com.example.master.repository.CdpoRepository;
import com.example.master.repository.DemandRepository;
import com.example.master.repository.DistrictRepository;
import com.example.master.repository.SupervisorRepository;
import com.example.master.services.DemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DemandServiceImpl implements DemandService {

    private final DemandRepository demandRepository;
    private final DemandEventPublisher eventPublisher;

    //  Inject repositories properly
    private final DistrictRepository districtRepository;
    private final CdpoRepository cdpoRepository;
    private final SupervisorRepository supervisorRepository;

    public DemandServiceImpl(
            DemandRepository demandRepository,
            DemandEventPublisher eventPublisher,
            DistrictRepository districtRepository,
            CdpoRepository cdpoRepository,
            SupervisorRepository supervisorRepository
    ) {
        this.demandRepository = demandRepository;
        this.eventPublisher = eventPublisher;
        this.districtRepository = districtRepository;
        this.cdpoRepository = cdpoRepository;
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public Demand createDemand(DemandDTO dto) {
        Demand demand = new Demand();

        demand.setDescription(dto.getDescription());
        demand.setStatus("PENDING");

        demand.setFromDate(dto.getFromDate());
        demand.setToDate(dto.getToDate());

        demand.setFciId(dto.getFciId());
        demand.setFciDocs(dto.getFciDocs());

        demand.setQuantity(dto.getQuantity());
        demand.setQuantityUnit(dto.getQuantityUnit());

        demand.setSupplierId(dto.getSupplierId());
        demand.setSupplierDocs(dto.getSupplierDocs());

        //  Set relations using repository instances, not static calls
        demand.setDistrict(districtRepository.getReferenceById(dto.getDistrictId()));
        demand.setCdpo(cdpoRepository.getReferenceById(dto.getCdpoId()));
        demand.setSupervisor(supervisorRepository.getReferenceById(dto.getSupervisorId()));

        //  Map AWC details
        List<DemandAwcDetail> awcDetails = dto.getAwcDetails().stream().map(awcDto -> {
            DemandAwcDetail detail = new DemandAwcDetail();
            detail.setDemand(demand);

            AnganwadiCenter awc = new AnganwadiCenter();
            awc.setId(awcDto.getAwcId());
            detail.setAnganwadi(awc);

            detail.setHcmNumber(awcDto.getHcmNumber());
            detail.setHcmUnit(awcDto.getHcmUnit());

            return detail;
        }).collect(Collectors.toList());
        demand.setAwcDetails(awcDetails);

        demand.setCreatedAt(LocalDateTime.now());
        demand.setUpdatedAt(LocalDateTime.now());

        Demand savedDemand = demandRepository.save(demand);

        eventPublisher.publish("NEW_DEMAND:" + savedDemand.getId());

        return savedDemand;
    }

    @Override
    public List<Demand> getAllDemands() {
        return demandRepository.findAll();
    }

    @Override
    public Demand getDemandById(Long id) {
        return demandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Demand not found with id: " + id));
    }

    @Override
    public void deleteDemand(Long id) {
        if (!demandRepository.existsById(id)) {
            throw new NotFoundException("Demand not found with id: " + id);
        }
        demandRepository.deleteById(id);
    }

    @Override
    public Demand updateStatus(Long id, String status) {
        Demand demand = getDemandById(id);

        validateStatusTransition(demand.getStatus(), status);

        demand.setStatus(status);
        demand.setUpdatedAt(LocalDateTime.now());

        // Timestamp logic
        switch (status) {
            case "FCI_ACCEPTED" -> demand.setFciAcceptedAt(LocalDateTime.now());
            case "FCI_REJECTED" -> demand.setFciRejectedAt(LocalDateTime.now());
            case "SUPPLIER_ACCEPTED" -> demand.setSupplierAcceptedAt(LocalDateTime.now());
            case "SUPPLIER_REJECTED" -> demand.setSupplierRejectedAt(LocalDateTime.now());
            case "CDPO_DISPATCHED" -> demand.setCdpoDispatchedAt(LocalDateTime.now());
            case "AWC_ACCEPTED" -> demand.setAwcAcceptedAt(LocalDateTime.now());
        }

        Demand updatedDemand = demandRepository.save(demand);

        // Publish event
        eventPublisher.publish("STATUS_UPDATE:" + id + ":" + status);

        return updatedDemand;
    }

    private void validateStatusTransition(String currentStatus, String newStatus) {
        boolean isValidTransition = switch (currentStatus) {
            case "PENDING" -> newStatus.equals("FCI_ACCEPTED") || newStatus.equals("FCI_REJECTED");
            case "FCI_ACCEPTED" -> newStatus.equals("SUPPLIER_ACCEPTED") || newStatus.equals("SUPPLIER_REJECTED");
            case "SUPPLIER_ACCEPTED" -> newStatus.equals("CDPO_DISPATCHED");
            case "CDPO_DISPATCHED" -> newStatus.equals("AWC_ACCEPTED");
            default -> false;
        };

        if (!isValidTransition) {
            throw new RuntimeException("Invalid status transition from " + currentStatus + " to " + newStatus);
        }
    }

    @Override
    public List<Demand> getDemandsByStatus(String status) {
        return demandRepository.findByStatus(status);
    }

    @Override
    public List<Demand> getPendingDemandsForFCI() {
        return demandRepository.findByStatus("PENDING");
    }

    @Override
    public List<Demand> getAcceptedDemandsForSupplier() {
        return demandRepository.findByStatus("FCI_ACCEPTED");
    }

    @Override
    public List<Demand> getManufacturedDemandsForCDPO() {
        return demandRepository.findByStatus("SUPPLIER_ACCEPTED");
    }

    @Override
    public List<Demand> getDispatchedDemandsForAWC() {
        return demandRepository.findByStatus("CDPO_DISPATCHED");
    }
}
