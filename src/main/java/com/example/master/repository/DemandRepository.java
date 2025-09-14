package com.example.master.repository;

import com.example.master.Dto.DemandResponseDTO;
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
//    @Query("SELECT d FROM Demand d WHERE d.status IN ('PENDING', 'FCI_ACCEPTED') ORDER BY d.createdAt ASC")
//    List<DemandResponseDTO> findPendingDemands();
    @Query("SELECT d FROM Demand d WHERE d.status IN ('PENDING', 'FCI_ACCEPTED') ORDER BY d.createdAt ASC")
    List<Demand> findPendingAndAcceptedDemandsForFci();

    // Supplier: FCI accepted or Supplier accepted
//    @Query("SELECT d FROM Demand d WHERE d.status IN ('FCI_ACCEPTED', 'FCI_DISPATCHED', 'SUPPLIER_ACCEPTED', 'SUPPLIER_SELF_DECLARED', 'SUPPLIER_DISPATCHED', 'CDPO_DISPATCHED','AWC_ACCEPTED', 'AWC_DISTRIBUTED') ORDER BY d.fciAcceptedAt ASC")
//    List<Demand> findAcceptedDemandsForSupplier();

    @Query("SELECT d FROM Demand d " +
            "WHERE d.status IN ('PENDING', 'FCI_ACCEPTED', 'FCI_DISPATCHED', 'SUPPLIER_ACCEPTED', 'SUPPLIER_SELF_DECLARED', 'SUPPLIER_DISPATCHED', 'CDPO_DISPATCHED','AWC_ACCEPTED', 'AWC_DISTRIBUTED') " +
            "AND d.supplier.id = :supplierId " +
            "ORDER BY d.fciAcceptedAt ASC")
    List<Demand> findAcceptedDemandsForSupplier(@Param("supplierId") Long supplierId);

    // CDPO: Supplier accepted or CDPO dispatched
    @Query("SELECT d FROM Demand d WHERE d.status IN ('SUPPLIER_ACCEPTED', 'SUPPLIER_SELF_DECLARED', 'SUPPLIER_DISPATCHED', 'CDPO_DISPATCHED','CDPO_ACCEPTED') ORDER BY d.supplierAcceptedAt ASC")
    List<Demand> findDemandsForCdpo();

    // AWC: CDPO dispatched or AWC accepted
    @Query("SELECT d FROM Demand d WHERE d.status IN ('CDPO_DISPATCHED', 'AWC_ACCEPTED') ORDER BY d.cdpoDispatchedAt ASC")
    List<Demand> findDemandsForAwc();

//    // Find FCI accepted demands (for Supplier)
//    @Query("SELECT d FROM Demand d WHERE d.status IN ('FCI_ACCEPTED', 'SUPPLIER_ACCEPTED') ORDER BY d.fciAcceptedAt ASC")
//    List<DemandResponseDTO> findFciAcceptedDemands();
//
//    // Find supplier accepted demands (for CDPO)
//    @Query("SELECT d FROM Demand d WHERE d.status IN ('SUPPLIER_ACCEPTED', 'CDPO_DISPATCHED') ORDER BY d.cdpoDispatchedAt ASC")
//    List<DemandResponseDTO> findSupplierAcceptedDemands();
//
//    // Find dispatched demands (for AWC)
//    @Query("SELECT d FROM Demand d WHERE d.status IN ('CDPO_DISPATCHED', 'AWC_ACCEPTED') ORDER BY d.cdpoDispatchedAt ASC")
//    List<DemandResponseDTO> findDispatchedDemands();

    // Find completed demands
    @Query("SELECT d FROM Demand d WHERE d.status = 'AWC_ACCEPTED' ORDER BY d.awcAcceptedAt DESC")
    List<DemandResponseDTO> findCompletedDemands();

    // Find rejected demands
    @Query("SELECT d FROM Demand d WHERE d.status LIKE '%_REJECTED' ORDER BY d.updatedAt DESC")
    List<DemandResponseDTO> findRejectedDemands();

    // Dashboard statistics
    @Query("SELECT d.status, COUNT(d) FROM Demand d GROUP BY d.status")
    List<Object[]> getStatusStatistics();

    // Recent demands (last 7 days)
    @Query("SELECT d FROM Demand d WHERE d.createdAt >= :weekAgo ORDER BY d.createdAt DESC")
    List<Demand> findRecentDemands(@Param("weekAgo") LocalDateTime weekAgo);
}
