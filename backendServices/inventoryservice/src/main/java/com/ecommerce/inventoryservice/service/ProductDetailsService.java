package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.productMetaData.CompleteProductMetaDataDTO;
import com.ecommerce.inventoryservice.dto.productMetaData.ProductMetaDataDTO;
import com.ecommerce.inventoryservice.dto.productMetaData.ProductUpdatedAndPublishedDTO;

public interface ProductDetailsService {

    String createProduct(ProductMetaDataDTO productMetaDataDTO);

    ProductUpdatedAndPublishedDTO updateProduct(CompleteProductMetaDataDTO completeProductMetaDataDTO);

    CompleteProductMetaDataDTO getProductDetails(String productId);

}
