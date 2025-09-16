package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.productImage.ProductImageDTO;

public interface ProductImageService {

    boolean addImageForAProduct(ProductImageDTO productImageDTO);

    byte[] getImageByProductIdAndImageName(String productId, String imageName);

    String getContentType(String productId, String imageName);

    boolean deleteImageFile(String productId, String imageName);

}
