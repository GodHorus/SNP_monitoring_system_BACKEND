package com.example.master.services.impl;

import com.example.master.Dto.DemandDTO;
import com.example.master.exception.NotFoundException;
import com.example.master.model.Demand;
import com.example.master.repository.DemandRepository;
import com.example.master.services.DemandService;
import com.example.master.event.DemandEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandServiceImpl implements DemandService {

    private final DemandRepository demandRepository;
    private final DemandEventPublisher eventPublisher;

    public DemandServiceImpl(DemandRepository demandRepository, DemandEventPublisher eventPublisher) {
        this.demandRepository = demandRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Demand createDemand(DemandDTO dto) {
        Demand demand = new Demand();
        demand.setDescription(dto.getDescription());
        demand.setFromDate(dto.getFromDate());
        demand.setToDate(dto.getToDate());
        demand.setFciId(dto.getFciId());
        demand.setFciDocs(dto.getFciDocs());
        demand.setQuantity(dto.getQuantity());
        demand.setQuantityUnit(dto.getQuantityUnit());
        demand.setSupplierId(dto.getSupplierId());
        demand.setSupplierDocs(dto.getSupplierDocs());
        demand.setStatus("DEMAND_GENERATED");

        Demand saved = demandRepository.save(demand);

        // publish Kafka event
        eventPublisher.publish("NEW_DEMAND:" + saved.getId());

        return saved;
    }

    @Override
    public List<Demand> getAllDemands() {
        return demandRepository.findAll();
    }

    @Override
    public Demand getDemandById(Long id) {
        return demandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Demand not found: " + id));
    }

    @Override
    public void deleteDemand(Long id) {
        demandRepository.deleteById(id);
    }

    @Override
    public void updateStatus(Long id, String status) {
        Demand demand = getDemandById(id);
        demand.setStatus(status);
        demandRepository.save(demand);

        // publish status update event
        eventPublisher.publish("STATUS_UPDATE:" + id + ":" + status);
    }
}
