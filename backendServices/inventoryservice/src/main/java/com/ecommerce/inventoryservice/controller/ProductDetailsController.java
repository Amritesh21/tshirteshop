package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.constants.ProductDetailsControllerMessages;
import com.ecommerce.inventoryservice.dto.ErrorResponseDTO;
import com.ecommerce.inventoryservice.dto.ProductDetailsAddedDTO;
import com.ecommerce.inventoryservice.dto.ProductDetailsDTO;
import com.ecommerce.inventoryservice.service.ProductDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory/product")
public class ProductDetailsController {

    private final ProductDetailsService productDetailsService;

    public ProductDetailsController(ProductDetailsService productDetailsService) {
        this.productDetailsService = productDetailsService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDetailsAddedDTO> createProduct(@RequestBody ProductDetailsDTO productDetailsDTO) {
        String productCreated = productDetailsService.createProduct(productDetailsDTO);
        if (productCreated != null && !productCreated.isEmpty()) {
            ProductDetailsAddedDTO productDetailsAddedDTO =
                    new ProductDetailsAddedDTO(ProductDetailsControllerMessages.PRODUCT_DETAILS_ADDED_SUCCESSFULLY, productCreated);
            return new ResponseEntity<>(productDetailsAddedDTO, HttpStatus.CREATED);
        } else {
            ProductDetailsAddedDTO productDetailsAddedDTO =
                    new ProductDetailsAddedDTO(ProductDetailsControllerMessages.UNABLE_TO_ADD_PRODUCT_DETAILS, null);
            return new ResponseEntity<>(productDetailsAddedDTO, HttpStatus.BAD_REQUEST);
        }
    }

}
