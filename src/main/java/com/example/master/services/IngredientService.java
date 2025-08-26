package com.example.master.services;

import com.example.master.Dto.IngredientDetailDTO;
import com.example.master.Dto.LabReportDTO;
import java.util.List;

public interface IngredientService {
    IngredientDetailDTO saveIngredient(IngredientDetailDTO dto);
    List<IngredientDetailDTO> getAllIngredients();
    IngredientDetailDTO getIngredientById(Long id);

    LabReportDTO saveLabReport(LabReportDTO dto);
    LabReportDTO getLabReportByIngredientId(Long ingredientId);
}
