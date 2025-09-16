package com.ecommerce.inventoryservice.dto.productMetaData;

import com.ecommerce.inventoryservice.constants.ProductDetailsControllerMessages;

public record ProductCreatedDTO(ProductDetailsControllerMessages message, String productId, boolean published) {
    @Override
    public String toString() {
        return "ProductCreatedDTO{" +
                "message=" + message +
                ", productId='" + productId + '\'' +
                ", published=" + published +
                '}';
    }
}
