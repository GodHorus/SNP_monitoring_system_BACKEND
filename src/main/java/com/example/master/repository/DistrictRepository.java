package com.example.master.repository;

import com.example.master.model.District;
import org.jboss.resteasy.annotations.Query;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
//    District findByDistrictName(String districtName);

//    @EntityGraph(attributePaths = {
//            "cdpos",
//            "cdpos.supervisors",
//            "cdpos.supervisors.anganwadiCenters"
//    })
//    @Query("SELECT d FROM District d")
//    List<District> findAllWithGraph();

}
