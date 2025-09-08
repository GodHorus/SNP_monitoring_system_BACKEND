//package com.example.master.controller;
//
//import com.example.master.Dto.ProductQuantityRequest;
//import com.example.master.Dto.ProductQuantityResponse;
//import com.example.master.services.ProductQuantityService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/api/product-quantities")
//public class ProductQuantityController {
//
//    private final ProductQuantityService productQuantityService;
//
//    public ProductQuantityController(ProductQuantityService productQuantityService) {
//        this.productQuantityService = productQuantityService;
//    }
//
//    @PostMapping
//    public ResponseEntity<ProductQuantityResponse> saveQuantities(@RequestBody ProductQuantityRequest request) {
//        return ResponseEntity.ok(productQuantityService.saveQuantities(request));
//    }
//}
