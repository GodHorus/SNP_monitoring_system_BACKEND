package com.example.master.controller;

import com.example.master.Dto.IngredientDetailDTO;
import com.example.master.Dto.IngredientDto;
import com.example.master.Dto.LabReportDTO;
import com.example.master.Dto.LabreportResponseDTO;
import com.example.master.model.Ingredient;
import com.example.master.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService service;

    public IngredientController(IngredientService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<Ingredient> create(@RequestBody IngredientDto dto){
        Ingredient ing = new Ingredient(dto.demandId, dto.name, dto.quantity, dto.unit);
        Ingredient saved = service.createIngredient(ing);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Ingredient>> createBulk(@RequestBody List<IngredientDto> dtos) {
        List<Ingredient> ingredients = dtos.stream()
                .map(dto -> new Ingredient(dto.demandId, dto.name, dto.quantity, dto.unit))
                .toList();

        List<Ingredient> saved = service.createIngredients(ingredients);
        return ResponseEntity.status(201).body(saved);
    }


    @GetMapping("/by-demand/{demandId}")
    public ResponseEntity<List<Ingredient>> listByDemand(@PathVariable Long demandId){
        return ResponseEntity.ok(service.findByDemandId(demandId));
    }

//    private final IngredientService ingredientService;
//
//    public IngredientController(IngredientService ingredientService) {
//        this.ingredientService = ingredientService;
//    }
//
//    @PostMapping
//    public ResponseEntity<List<IngredientDetailDTO>> createIngredients(@RequestBody List<IngredientDetailDTO> ingredients) {
//        return ResponseEntity.ok(ingredientService.saveIngredients(ingredients));
//    }
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
//    @GetMapping("/by-demand")
//    public ResponseEntity<List<IngredientDetailDTO>> getByDemandId(@RequestParam Long demandId) {
//        return ResponseEntity.ok(ingredientService.getIngredientsByDemandId(demandId));
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
//
//    @GetMapping("/lab-report/{id}")
//    public ResponseEntity<LabreportResponseDTO> getLabReportById(@PathVariable Long id) {
//        return ResponseEntity.ok(ingredientService.getLabReportById(id));
//    }

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
