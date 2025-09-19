package com.example.master.services;

import com.example.master.Dto.IngredientDetailDTO;
import com.example.master.Dto.LabReportDTO;
import com.example.master.Dto.LabreportResponseDTO;
import com.example.master.model.Ingredient;

import java.util.List;

public interface IngredientService {
//    IngredientDetailDTO saveIngredient(IngredientDetailDTO dto);
//    List<IngredientDetailDTO> getAllIngredients();
//    IngredientDetailDTO getIngredientById(Long id);
//
//    LabReportDTO saveLabReport(LabReportDTO dto);
//    LabReportDTO getLabReportByIngredientId(Long ingredientId);

//    IngredientDetailDTO saveIngredient(IngredientDetailDTO dto);
//    List<IngredientDetailDTO> saveIngredients(List<IngredientDetailDTO> dtos);
//
//    List<IngredientDetailDTO> getAllIngredients();
//    IngredientDetailDTO getIngredientById(Long id);
//
//    LabReportDTO saveLabReport(LabReportDTO dto);

    //    LabReportDTO getLabReportByIngredientId(Long ingredientId);
//
//    LabReportDTO getLabReportByIngredientId(Long ingredientId);
//
//    List<IngredientDetailDTO> getIngredientsByDemandId(Long demandId);
//
//    LabreportResponseDTO getLabReportById(Long labReportId);

    Ingredient createIngredient(Ingredient ingredient);

    List<Ingredient> createIngredients(List<Ingredient> ingredients);

    List<Ingredient> findByDemandId(Long demandId);

}
