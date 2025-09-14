package com.example.master.mapper;

import com.example.master.Dto.AWCDispatchDTO;
import com.example.master.model.AWCDispatch;
import com.example.master.model.AnganwadiCenter;
import com.example.master.model.Demand;

public class AWCDispatchMapper {

    public static AWCDispatchDTO toDTO(AWCDispatch entity) {
        AWCDispatchDTO dto = new AWCDispatchDTO();
        dto.setId(entity.getId());
        dto.setAnganwadiId(entity.getAnganwadiCenter() != null ? entity.getAnganwadiCenter().getId() : null);
        dto.setDemandId(entity.getDemand() != null ? entity.getDemand().getId() : null);
        dto.setSublotNo(entity.getSublotNo());
        dto.setBenficiaryCount(entity.getBenficiaryCount());
        dto.setDistributedPackets(entity.getDistributedPackets());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static AWCDispatch toEntity(AWCDispatchDTO dto, AnganwadiCenter anganwadi, Demand demand) {
        AWCDispatch entity = new AWCDispatch();
        entity.setId(dto.getId());
        entity.setAnganwadiCenter(anganwadi);
        entity.setDemand(demand);
        entity.setSublotNo(dto.getSublotNo());
        entity.setBenficiaryCount(dto.getBenficiaryCount());
        entity.setDistributedPackets(dto.getDistributedPackets());
        return entity;
    }
}
