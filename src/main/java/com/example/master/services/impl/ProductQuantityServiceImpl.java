//package com.example.master.services.impl;
//
//import com.example.master.repository.DemandProductRepository;
//import com.example.master.repository.CommodityRepository;
//import com.example.master.repository.ProductCommodityQuantityRepository;
//import com.example.master.model.DemandProduct;
//import com.example.master.model.Commodity;
//import com.example.master.model.ProductCommodityQuantity;
//import com.example.master.Dto.ProductQuantityRequest;
//import com.example.master.Dto.ProductQuantityResponse;
//import com.example.master.services.ProductQuantityService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class ProductQuantityServiceImpl implements ProductQuantityService {
//
//    private final DemandProductRepository demandProductRepository;
//    private final CommodityRepository commodityRepository;
//    private final ProductCommodityQuantityRepository productCommodityQuantityRepository;
//
//    public ProductQuantityServiceImpl(DemandProductRepository demandProductRepository,
//                                      CommodityRepository commodityRepository,
//                                      ProductCommodityQuantityRepository productCommodityQuantityRepository) {
//        this.demandProductRepository = demandProductRepository;
//        this.commodityRepository = commodityRepository;
//        this.productCommodityQuantityRepository = productCommodityQuantityRepository;
//    }
//
//    @Override
//    @Transactional
//    public ProductQuantityResponse saveQuantities(ProductQuantityRequest request) {
//        DemandProduct demandProduct = demandProductRepository.findById(request.getDemandProductId())
//                .orElseThrow(() -> new RuntimeException("DemandProduct not found"));
//
//        // Do NOT clear old quantities, just create new ones
//        List<ProductCommodityQuantity> newQuantities = new ArrayList<>();
//
//        for (Map.Entry<Long, Double> entry : request.getCommodityQuantities().entrySet()) {
//            Commodity commodity = commodityRepository.findById(entry.getKey())
//                    .orElseThrow(() -> new RuntimeException("Commodity not found: " + entry.getKey()));
//
//            ProductCommodityQuantity pcq = new ProductCommodityQuantity();
//            pcq.setCommodity(commodity);
//            pcq.setQuantity(entry.getValue());
//            pcq.setDemandProduct(demandProduct);
//
//            newQuantities.add(pcq);
//        }
//
//        // Add new quantities to the list
//        demandProduct.getProductQuantities().addAll(newQuantities);
//
//        // Save demand product (cascades to new ProductCommodityQuantity entries)
//        demandProductRepository.save(demandProduct);
//
//        // build response
//        ProductQuantityResponse response = new ProductQuantityResponse();
//        response.setDemandProductId(demandProduct.getId());
//        response.setProductType(demandProduct.getType());
//        Map<String, Double> mapped = newQuantities.stream()
//                .collect(Collectors.toMap(q -> q.getCommodity().getName(), ProductCommodityQuantity::getQuantity));
//        response.setCommodityQuantities(mapped);
//
//        return response;
//    }
//
//}
