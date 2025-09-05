package com.example.master.services.impl;

import com.example.master.Dto.BeneficiaryDTO;
import com.example.master.model.Benificiary;
import com.example.master.repository.BeneficiaryRepository;
import com.example.master.services.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    @Autowired
    public BeneficiaryServiceImpl(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }

    @Override
    public List<Benificiary> getAllBeneficiaries() {
        return beneficiaryRepository.findAll();
    }

    @Override
    public Optional<Benificiary> getBeneficiaryById(Long id) {
        return beneficiaryRepository.findById(id);
    }

    @Override
    public Benificiary createBeneficiary(BeneficiaryDTO beneficiaryDTO) {
        Benificiary beneficiary = new Benificiary();
        beneficiary.setBeneficiaryName(beneficiaryDTO.getBeneficiaryName());
        return beneficiaryRepository.save(beneficiary);
    }

    @Override
    public Benificiary updateBeneficiary(Long id, BeneficiaryDTO beneficiaryDTO) {
        Optional<Benificiary> existingBeneficiary = beneficiaryRepository.findById(id);
        if (existingBeneficiary.isPresent()) {
            Benificiary beneficiary = existingBeneficiary.get();
            beneficiary.setBeneficiaryName(beneficiaryDTO.getBeneficiaryName());
            return beneficiaryRepository.save(beneficiary);
        }
        return null;
    }

    @Override
    public void deleteBeneficiary(Long id) {
        beneficiaryRepository.deleteById(id);
    }
}
