package com.example.master.repository;

import com.example.master.model.CDPOSupplierDispatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CDPOSupplierDispatchRepository extends JpaRepository<CDPOSupplierDispatch, Long> {
    @Query("SELECT d.sublotNo FROM CDPOSupplierDispatch d ORDER BY d.id DESC LIMIT 1")
    String findLastSublotNo();
}
