//package com.example.master.controller;
//
//import com.example.master.Dto.CDPOSupplierDispatchDTO;
//import com.example.master.services.CDPOSupplierDispatchService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/dispatches")
//public class CDPOSupplierDispatchController {
//
//    private final CDPOSupplierDispatchService dispatchService;
//
//    public CDPOSupplierDispatchController(CDPOSupplierDispatchService dispatchService) {
//        this.dispatchService = dispatchService;
//    }
//
//    @PostMapping
//    public ResponseEntity<CDPOSupplierDispatchDTO> createDispatch(@RequestBody CDPOSupplierDispatchDTO dto) {
//        return ResponseEntity.ok(dispatchService.createDispatch(dto));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CDPOSupplierDispatchDTO> getDispatch(@PathVariable Long id) {
//        return ResponseEntity.ok(dispatchService.getDispatchById(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CDPOSupplierDispatchDTO>> getAllDispatches() {
//        return ResponseEntity.ok(dispatchService.getAllDispatches());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDispatch(@PathVariable Long id) {
//        dispatchService.deleteDispatch(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PostMapping("/bulk")
//    public ResponseEntity<List<CDPOSupplierDispatchDTO>> createDispatches(
//            @RequestBody List<CDPOSupplierDispatchDTO> dtos) {
//        return ResponseEntity.ok(dispatchService.createDispatches(dtos));
//    }
//}
