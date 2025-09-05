package com.example.master.services;

import com.example.master.Dto.BeneficiaryDTO;
import com.example.master.model.Benificiary;

import java.util.List;
import java.util.Optional;

public interface BeneficiaryService {

    List<Benificiary> getAllBeneficiaries();

    Optional<Benificiary> getBeneficiaryById(Long id);

    Benificiary createBeneficiary(BeneficiaryDTO beneficiaryDTO);

    Benificiary updateBeneficiary(Long id, BeneficiaryDTO beneficiaryDTO);

    void deleteBeneficiary(Long id);
}
