// repository/BlockRepository.java
package com.example.master.repository;

import com.example.master.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlockRepository extends JpaRepository<Block, Long> {
    Optional<Block> findByBlockName(String blockName);
}
