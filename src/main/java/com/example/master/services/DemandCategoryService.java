package com.example.master.services;
import com.example.master.Dto.DemandCategoryDTO;
import com.example.master.model.DemandCategory;

import java.util.List;
import java.util.Optional;

public interface DemandCategoryService {

    List<DemandCategory> getAllDemandCategories();

    Optional<DemandCategory> getDemandCategoryById(Long id);

    DemandCategory createDemandCategory(DemandCategoryDTO demandCategoryDTO);

    DemandCategory updateDemandCategory(Long id, DemandCategoryDTO demandCategoryDTO);

    void deleteDemandCategory(Long id);
}
