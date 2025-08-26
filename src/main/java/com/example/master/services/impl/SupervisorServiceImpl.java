package com.example.master.services.impl;

import com.example.master.model.Cdpo;
import com.example.master.model.Supervisor;
import com.example.master.repository.CdpoRepository;
import com.example.master.repository.SupervisorRepository;
import com.example.master.services.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    private final SupervisorRepository supervisorRepository;
    private final CdpoRepository cdpoRepository;

    @Autowired
    public SupervisorServiceImpl(SupervisorRepository supervisorRepository, CdpoRepository cdpoRepository) {
        this.supervisorRepository = supervisorRepository;
        this.cdpoRepository = cdpoRepository;
    }

    @Override
    public Supervisor createSupervisor(Supervisor supervisor, Long cdpoId) {
        // Fetch managed Cdpo from DB
        Cdpo cdpo = cdpoRepository.findById(cdpoId)
                .orElseThrow(() -> new RuntimeException("Cdpo not found with id: " + cdpoId));

        supervisor.setCdpo(cdpo); // attach managed entity
        return supervisorRepository.save(supervisor);
    }
}
