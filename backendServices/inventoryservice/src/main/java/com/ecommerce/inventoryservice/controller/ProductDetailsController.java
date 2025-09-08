package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.dto.ProductDetailsDTO;
import com.ecommerce.inventoryservice.entity.Product;
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
    public ResponseEntity<Boolean> createProduct(@RequestBody ProductDetailsDTO productDetailsDTO) {
        boolean productCreated = productDetailsService.createProduct(productDetailsDTO);
        if (productCreated) {
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_GATEWAY);
        }
    }

}
