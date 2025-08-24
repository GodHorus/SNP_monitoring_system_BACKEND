// repository/SupervisorRepository.java
package com.example.master.repository;

import com.example.master.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    Optional<Supervisor> findByName(String name);
}
