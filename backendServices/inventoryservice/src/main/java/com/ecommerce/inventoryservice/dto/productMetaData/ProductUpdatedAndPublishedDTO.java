package com.ecommerce.inventoryservice.dto.productMetaData;

public record ProductUpdatedAndPublishedDTO(boolean updated, boolean published) {

    @Override
    public String toString() {
        return "ProductUpdatedAndPublished{" +
                "updated=" + updated +
                ", published=" + published +
                '}';
    }
}
