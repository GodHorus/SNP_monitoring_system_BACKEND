package com.example.master.controller;

import com.example.master.Dto.IngredientDetailDTO;
import com.example.master.Dto.LabReportDTO;
import com.example.master.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<List<IngredientDetailDTO>> createIngredients(@RequestBody List<IngredientDetailDTO> ingredients) {
        return ResponseEntity.ok(ingredientService.saveIngredients(ingredients));
    }

    @GetMapping
    public ResponseEntity<List<IngredientDetailDTO>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDetailDTO> getIngredientById(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @GetMapping("/by-demand")
    public ResponseEntity<List<IngredientDetailDTO>> getByDemandId(@RequestParam Long demandId) {
        return ResponseEntity.ok(ingredientService.getIngredientsByDemandId(demandId));
    }

    @PostMapping("/lab-report")
    public ResponseEntity<LabReportDTO> createLabReport(@RequestBody LabReportDTO dto) {
        return ResponseEntity.ok(ingredientService.saveLabReport(dto));
    }

    @GetMapping("/{id}/lab-report")
    public ResponseEntity<LabReportDTO> getLabReportByIngredient(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.getLabReportByIngredientId(id));
    }

//    private final IngredientService ingredientService;
//
//    public IngredientController(IngredientService ingredientService) {
//        this.ingredientService = ingredientService;
//    }
//
//    @PostMapping
//    public ResponseEntity<IngredientDetailDTO> createIngredient(@RequestBody IngredientDetailDTO dto) {
//        return ResponseEntity.ok(ingredientService.saveIngredient(dto));
//    }
//
////    @PostMapping
////    public ResponseEntity<List<IngredientDetailDTO>> createIngredients(@RequestBody List<IngredientDetailDTO> ingredients) {
////        List<IngredientDetailDTO> savedIngredients = Collections.singletonList(ingredientService.saveIngredients((IngredientDetailDTO) ingredients));
////        return ResponseEntity.ok(savedIngredients);
////    }
//
//    @GetMapping
//    public ResponseEntity<List<IngredientDetailDTO>> getAllIngredients() {
//        return ResponseEntity.ok(ingredientService.getAllIngredients());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<IngredientDetailDTO> getIngredientById(@PathVariable Long id) {
//        return ResponseEntity.ok(ingredientService.getIngredientById(id));
//    }
//
//    @PostMapping("/lab-report")
//    public ResponseEntity<LabReportDTO> createLabReport(@RequestBody LabReportDTO dto) {
//        return ResponseEntity.ok(ingredientService.saveLabReport(dto));
//    }
//
//    @GetMapping("/{id}/lab-report")
//    public ResponseEntity<LabReportDTO> getLabReportByIngredient(@PathVariable Long id) {
//        return ResponseEntity.ok(ingredientService.getLabReportByIngredientId(id));
//    }
}
