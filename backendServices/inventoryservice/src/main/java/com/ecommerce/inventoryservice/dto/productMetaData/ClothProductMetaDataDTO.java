package com.ecommerce.inventoryservice.dto.productMetaData;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClothProductMetaDataDTO extends CompleteProductMetaDataDTO {

    List<String> sizes;

    List<String> colors;

    List<String> brands;

    @Override
    public String toString() {
        return "ClothProductMetaDataDTO{" +
                "sizes=" + sizes +
                ", colors=" + colors +
                ", brands=" + brands +
                ", price=" + price +
                ", stock=" + stock +
                ", publish=" + publish +
                ", maxPurchaseLimit=" + maxPurchaseLimit +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
