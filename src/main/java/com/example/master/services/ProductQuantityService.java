package com.example.master.services;

import com.example.master.Dto.ProductQuantityRequest;
import com.example.master.Dto.ProductQuantityResponse;

public interface ProductQuantityService {
    ProductQuantityResponse saveQuantities(ProductQuantityRequest request);
}
