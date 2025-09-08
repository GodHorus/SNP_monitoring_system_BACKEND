package com.example.master.controller;

import com.example.master.Dto.DemandProductDTO;
import com.example.master.model.DemandProduct;
import com.example.master.services.DemandProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demand-products")
public class DemandProductController {

    @Autowired
    private DemandProductService demandProductService;

    // Convert entity to DTO
    private DemandProductDTO convertToDTO(DemandProduct demandProduct) {
        DemandProductDTO dto = new DemandProductDTO();
        dto.setId(demandProduct.getId());
        dto.setType(demandProduct.getType());
        return dto;
    }

    // Convert DTO to entity
    private DemandProduct convertToEntity(DemandProductDTO dto) {
        DemandProduct demandProduct = new DemandProduct();
        demandProduct.setId(dto.getId());
        demandProduct.setType(dto.getType());
        return demandProduct;
    }

    // Create a new demand product
    @PostMapping
    public ResponseEntity<DemandProductDTO> createDemandProduct(@RequestBody DemandProductDTO demandProductDTO) {
        DemandProduct demandProduct = convertToEntity(demandProductDTO);
        DemandProduct savedDemandProduct = demandProductService.createDemandProduct(demandProduct);
        DemandProductDTO savedDTO = convertToDTO(savedDemandProduct);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    // Get all demand products
    @GetMapping
    public ResponseEntity<com.example.master.Dto.DemandProduct[]> getAllDemandProducts() {
//        List<DemandProduct> demandProducts = demandProductService.getAllDemandProducts();
//        List<DemandProductDTO> demandProductDTOs = demandProducts.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(demandProductDTOs, HttpStatus.OK);
        return new ResponseEntity<>(com.example.master.Dto.DemandProduct.values(), HttpStatus.OK);
    }

    // Get a single demand product by ID
    @GetMapping("/{id}")
    public ResponseEntity<DemandProductDTO> getDemandProductById(@PathVariable Long id) {
        DemandProduct demandProduct = demandProductService.getDemandProductById(id);
        if (demandProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        DemandProductDTO dto = convertToDTO(demandProduct);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Update an existing demand product
    @PutMapping("/{id}")
    public ResponseEntity<DemandProductDTO> updateDemandProduct(@PathVariable Long id,
                                                                @RequestBody DemandProductDTO demandProductDTO) {
        DemandProduct demandProduct = convertToEntity(demandProductDTO);
        demandProduct.setId(id);
        DemandProduct updatedDemandProduct = demandProductService.updateDemandProduct(demandProduct);
        if (updatedDemandProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        DemandProductDTO dto = convertToDTO(updatedDemandProduct);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Delete a demand product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemandProduct(@PathVariable Long id) {
        boolean isDeleted = demandProductService.deleteDemandProduct(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
