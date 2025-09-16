package com.ecommerce.inventoryservice.dto.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponseDTO {

    String statusCode;

    String message;

    String path;

}
