package com.ecommerce.inventoryservice.dto.productImage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class ProductImageDTO {

    String productId;

    MultipartFile imageFile;

    String imageName;

}
