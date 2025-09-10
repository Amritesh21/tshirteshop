package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.ProductDetailsDTO;
import org.bson.types.ObjectId;

public interface ProductDetailsService {

    String createProduct(ProductDetailsDTO productDetailsDTO);

}
