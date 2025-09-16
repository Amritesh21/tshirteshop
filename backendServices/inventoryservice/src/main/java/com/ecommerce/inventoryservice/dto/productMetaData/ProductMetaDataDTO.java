package com.ecommerce.inventoryservice.dto.productMetaData;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductMetaDataDTO {

    @NotEmpty(message = "Product name cannot be empty")
    String productName;

    @NotEmpty(message = "Product description cannot be empty")
    String description;

    @NotEmpty(message = "Product category cannot be empty")
    String category;

}
