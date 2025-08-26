//package com.example.master.controller;
//
//import com.example.master.model.District;
//import com.example.master.model.Cdpo;
//import com.example.master.model.Supervisor;
//import com.example.master.model.AnganwadiCenter;
//import com.example.master.repository.DistrictRepository;
//import com.example.master.repository.CdpoRepository;
//import com.example.master.repository.SupervisorRepository;
//import com.example.master.repository.AnganwadiCenterRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class MasterDataController {
//
//    @Autowired
//    private DistrictRepository districtRepository;
//
//    @Autowired
//    private CdpoRepository cdpoRepository;
//
//    @Autowired
//    private SupervisorRepository supervisorRepository;
//
//    @Autowired
//    private AnganwadiCenterRepository anganwadiCenterRepository;
//
//    // Get all districts with their corresponding Cdpos, Supervisors, and AnganwadiCenters
//    @GetMapping("/districts")
//    public ResponseEntity<List<District>> getAllDistricts() {
//        List<District> districts = districtRepository.findAll();
//        return ResponseEntity.ok(districts);
//    }
//
//    // Get all Cdpos for a specific district by ID
//    @GetMapping("/districts/{districtId}/cdpos")
//    public ResponseEntity<List<Cdpo>> getCdposByDistrict(@PathVariable Long districtId) {
//        List<Cdpo> cdpos = cdpoRepository.findByDistrictId(districtId);
//        return ResponseEntity.ok(cdpos);
//    }
//
//    // Get all Supervisors under a specific Cdpo
//    @GetMapping("/cdpos/{cdpoId}/supervisors")
//    public ResponseEntity<List<Supervisor>> getSupervisorsByCdpo(@PathVariable Long cdpoId) {
//        List<Supervisor> supervisors = supervisorRepository.findByCdpoId(cdpoId);
//        return ResponseEntity.ok(supervisors);
//    }
//
//    // Get all AnganwadiCenters under a specific Supervisor
//    @GetMapping("/supervisors/{supervisorId}/anganwadi-centers")
//    public ResponseEntity<List<AnganwadiCenter>> getAnganwadiCentersBySupervisor(@PathVariable Long supervisorId) {
//        List<AnganwadiCenter> anganwadiCenters = anganwadiCenterRepository.findBySupervisorId(supervisorId);
//        return ResponseEntity.ok(anganwadiCenters);
//    }
//}
