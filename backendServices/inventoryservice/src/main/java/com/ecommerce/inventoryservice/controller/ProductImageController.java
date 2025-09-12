package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.dto.ProductImageDTO;
import com.ecommerce.inventoryservice.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/inventory/product/image")
@CrossOrigin("*")
public class ProductImageController {

    private final ProductImageService productImageService;

    @Autowired
    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addProductImage(
                                                    @RequestParam("totalImages") int totalImages,
                                                    @RequestParam String productId,
                                                    @RequestParam String imageName,
                                                    @RequestParam MultipartFile imageFile
                                                   ) {
        ProductImageDTO productImageDTO = ProductImageDTO
                .builder()
                .imageFile(imageFile)
                .productId(productId)
                .imageName(imageName)
                .build();
        boolean addedImageFlag = productImageService.addImageForAProduct(productImageDTO, totalImages);
        if (addedImageFlag) {
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }

}
