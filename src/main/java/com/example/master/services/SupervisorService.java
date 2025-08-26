package com.example.master.services;

import com.example.master.model.Supervisor;

public interface SupervisorService {
    Supervisor createSupervisor(Supervisor supervisor, Long cdpoId);
}
