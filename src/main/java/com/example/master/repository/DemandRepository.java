package com.example.master.repository;

import com.example.master.model.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Long> {

    // Find demands by status
    List<Demand> findByStatus(String status);

    // Find demands by status ordered by creation date
    List<Demand> findByStatusOrderByCreatedAtDesc(String status);

    // Find demands created between dates
    List<Demand> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Custom query to get demands with status in a list
    @Query("SELECT d FROM Demand d WHERE d.status IN :statuses ORDER BY d.updatedAt DESC")
    List<Demand> findByStatusIn(@Param("statuses") List<String> statuses);

    // Get count of demands by status
    @Query("SELECT COUNT(d) FROM Demand d WHERE d.status = :status")
    Long countByStatus(@Param("status") String status);

    // Find pending demands (for FCI)
    @Query("SELECT d FROM Demand d WHERE d.status = 'PENDING' ORDER BY d.createdAt ASC")
    List<Demand> findPendingDemands();

    // Find FCI accepted demands (for Supplier)
    @Query("SELECT d FROM Demand d WHERE d.status = 'FCI_ACCEPTED' ORDER BY d.fciAcceptedAt ASC")
    List<Demand> findFciAcceptedDemands();

    // Find supplier accepted demands (for CDPO)
    @Query("SELECT d FROM Demand d WHERE d.status = 'SUPPLIER_ACCEPTED' ORDER BY d.supplierAcceptedAt ASC")
    List<Demand> findSupplierAcceptedDemands();

    // Find dispatched demands (for AWC)
    @Query("SELECT d FROM Demand d WHERE d.status = 'CDPO_DISPATCHED' ORDER BY d.cdpoDispatchedAt ASC")
    List<Demand> findDispatchedDemands();

    // Find completed demands
    @Query("SELECT d FROM Demand d WHERE d.status = 'AWC_ACCEPTED' ORDER BY d.awcAcceptedAt DESC")
    List<Demand> findCompletedDemands();

    // Find rejected demands
    @Query("SELECT d FROM Demand d WHERE d.status LIKE '%_REJECTED' ORDER BY d.updatedAt DESC")
    List<Demand> findRejectedDemands();

    // Dashboard statistics
    @Query("SELECT d.status, COUNT(d) FROM Demand d GROUP BY d.status")
    List<Object[]> getStatusStatistics();

    // Recent demands (last 7 days)
    @Query("SELECT d FROM Demand d WHERE d.createdAt >= :weekAgo ORDER BY d.createdAt DESC")
    List<Demand> findRecentDemands(@Param("weekAgo") LocalDateTime weekAgo);
}
