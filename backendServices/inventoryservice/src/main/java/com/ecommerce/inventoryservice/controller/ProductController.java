package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.constants.ProductDetailsControllerMessages;
import com.ecommerce.inventoryservice.dto.productMetaData.*;
import com.ecommerce.inventoryservice.service.ProductDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller/product")
public class ProductController {

    private final ProductDetailsService productDetailsService;

    public ProductController(ProductDetailsService productDetailsService) {
        this.productDetailsService = productDetailsService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductCreatedDTO> createProduct(@RequestBody ProductMetaDataDTO productMetaDataDTO) {
        String productCreated = productDetailsService.createProduct(productMetaDataDTO);
        if (productCreated != null && !productCreated.isEmpty()) {
            ProductCreatedDTO productCreatedDTO = new ProductCreatedDTO(ProductDetailsControllerMessages.PRODUCT_DETAILS_ADDED_SUCCESSFULLY, productCreated, false);
            return new ResponseEntity<>(productCreatedDTO, HttpStatus.CREATED);
        } else {
            ProductCreatedDTO productCreatedDTO = new ProductCreatedDTO(ProductDetailsControllerMessages.UNABLE_TO_ADD_PRODUCT_DETAILS, null, false);
            return new ResponseEntity<>(productCreatedDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ProductUpdatedAndPublishedDTO> updateProduct(@RequestBody CompleteProductMetaDataDTO completeProductMetaDataDTO) {
       var isProductUpdatedAndPublished = productDetailsService.updateProduct(completeProductMetaDataDTO);
       return new ResponseEntity<>(isProductUpdatedAndPublished, HttpStatus.OK);
    }

    @GetMapping("/fetch/{productId}")
    public ResponseEntity<CompleteProductMetaDataDTO> getProductDetails(@PathVariable String productId) {
        CompleteProductMetaDataDTO completeProductMetaDataDTO = productDetailsService.getProductDetails(productId);
        return new ResponseEntity<>(completeProductMetaDataDTO, HttpStatus.OK);
    }

}
