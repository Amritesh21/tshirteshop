package com.ecommerce.inventoryservice.dto.productMetaData;

import com.ecommerce.inventoryservice.constants.Category;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "category",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClothProductMetaDataDTO.class, name = "CLOTH"),
        @JsonSubTypes.Type(value = CosmeticProductMetaDataDTO.class, name = "COSMETIC")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompleteProductMetaDataDTO extends ProductMetaDataDTO {

    @NotEmpty(message = "Product must have a price")
    Long price;

    Long stock;

    boolean publish;

    Long maxPurchaseLimit;

    String productId;

}
