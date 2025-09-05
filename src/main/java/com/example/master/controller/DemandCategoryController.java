package com.example.master.controller;

import com.example.master.Dto.DemandCategoryDTO;
import com.example.master.model.DemandCategory;
import com.example.master.services.DemandCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/demand-categories")
public class DemandCategoryController {

    private final DemandCategoryService demandCategoryService;

    @Autowired
    public DemandCategoryController(DemandCategoryService demandCategoryService) {
        this.demandCategoryService = demandCategoryService;
    }

    @GetMapping
    public List<DemandCategory> getAllDemandCategories() {
        return demandCategoryService.getAllDemandCategories();
    }

    @GetMapping("/{id}")
    public Optional<DemandCategory> getDemandCategoryById(@PathVariable Long id) {
        return demandCategoryService.getDemandCategoryById(id);
    }

    @PostMapping
    public DemandCategory createDemandCategory(@RequestBody DemandCategoryDTO demandCategoryDTO) {
        return demandCategoryService.createDemandCategory(demandCategoryDTO);
    }

    @PutMapping("/{id}")
    public DemandCategory updateDemandCategory(@PathVariable Long id,
                                               @RequestBody DemandCategoryDTO demandCategoryDTO) {
        return demandCategoryService.updateDemandCategory(id, demandCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDemandCategory(@PathVariable Long id) {
        demandCategoryService.deleteDemandCategory(id);
    }
}
