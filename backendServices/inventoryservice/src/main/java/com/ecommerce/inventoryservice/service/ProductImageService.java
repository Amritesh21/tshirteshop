package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.ProductImageDTO;

public interface ProductImageService {

    boolean addImageForAProduct(ProductImageDTO productImageDTO, int totalImagesCount);

}
