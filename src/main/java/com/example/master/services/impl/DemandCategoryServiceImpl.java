package com.example.master.services.impl;


import com.example.master.Dto.DemandCategoryDTO;
import com.example.master.model.DemandCategory;
import com.example.master.repository.DemandCategoryRepository;
import com.example.master.services.DemandCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandCategoryServiceImpl implements DemandCategoryService {

    private final DemandCategoryRepository demandCategoryRepository;

    @Autowired
    public DemandCategoryServiceImpl(DemandCategoryRepository demandCategoryRepository) {
        this.demandCategoryRepository = demandCategoryRepository;
    }

    @Override
    public List<DemandCategory> getAllDemandCategories() {
        return demandCategoryRepository.findAll();
    }

    @Override
    public Optional<DemandCategory> getDemandCategoryById(Long id) {
        return demandCategoryRepository.findById(id);
    }

    @Override
    public DemandCategory createDemandCategory(DemandCategoryDTO demandCategoryDTO) {
        DemandCategory demandCategory = new DemandCategory();
        demandCategory.setCategory(demandCategoryDTO.getCategory());
        return demandCategoryRepository.save(demandCategory);
    }

    @Override
    public DemandCategory updateDemandCategory(Long id, DemandCategoryDTO demandCategoryDTO) {
        Optional<DemandCategory> existingCategory = demandCategoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            DemandCategory demandCategory = existingCategory.get();
            demandCategory.setCategory(demandCategoryDTO.getCategory());
            return demandCategoryRepository.save(demandCategory);
        }
        return null;
    }

    @Override
    public void deleteDemandCategory(Long id) {
        demandCategoryRepository.deleteById(id);
    }
}
