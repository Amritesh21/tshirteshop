package com.ecommerce.inventoryservice.dto;

import com.ecommerce.inventoryservice.constants.ProductDetailsControllerMessages;

public record ProductDetailsAddedDTO(ProductDetailsControllerMessages message, String productId) {
    @Override
    public String toString() {
        return "ProductDetailsAddedDTO{" +
                "message='" + message + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
