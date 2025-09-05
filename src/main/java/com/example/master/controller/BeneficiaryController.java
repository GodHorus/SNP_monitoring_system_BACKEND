package com.example.master.controller;

import com.example.master.Dto.BeneficiaryDTO;
import com.example.master.model.Benificiary;
import com.example.master.services.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/beneficiaries")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @Autowired
    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @GetMapping
    public List<Benificiary> getAllBeneficiaries() {
        return beneficiaryService.getAllBeneficiaries();
    }

    @GetMapping("/{id}")
    public Optional<Benificiary> getBeneficiaryById(@PathVariable Long id) {
        return beneficiaryService.getBeneficiaryById(id);
    }

    @PostMapping
    public Benificiary createBeneficiary(@RequestBody BeneficiaryDTO beneficiaryDTO) {
        return beneficiaryService.createBeneficiary(beneficiaryDTO);
    }

    @PutMapping("/{id}")
    public Benificiary updateBeneficiary(@PathVariable Long id, @RequestBody BeneficiaryDTO beneficiaryDTO) {
        return beneficiaryService.updateBeneficiary(id, beneficiaryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBeneficiary(@PathVariable Long id) {
        beneficiaryService.deleteBeneficiary(id);
    }
}
