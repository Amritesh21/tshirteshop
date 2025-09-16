package com.ecommerce.inventoryservice.dto.productMetaData;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CosmeticProductMetaDataDTO extends CompleteProductMetaDataDTO {

    String brand;

    String manufacturedOn;

    String useBy;



}
