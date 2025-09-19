package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.dto.productImage.ProductImageDTO;
import com.ecommerce.inventoryservice.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/seller/product/image")
@CrossOrigin("*")
public class ProductImageController {

    private final ProductImageService productImageService;

    @Autowired
    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addProductImage(@RequestParam String productId,
                                                   @RequestParam String imageName,
                                                   @RequestParam MultipartFile imageFile
                                                   ) {
        ProductImageDTO productImageDTO = ProductImageDTO
                .builder()
                .imageFile(imageFile)
                .productId(productId)
                .imageName(imageName)
                .build();
        boolean addedImageFlag = productImageService.addImageForAProduct(productImageDTO);
        if (addedImageFlag) {
            return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fetch/{productId}/{imageName}")
    public ResponseEntity<byte[]> getImageByProductIdAndName(@PathVariable String productId, @PathVariable String imageName) {
        var imageBytes = productImageService.getImageByProductIdAndImageName(productId, imageName);
        var contentType = productImageService.getContentType(productId, imageName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(imageBytes);
    }

    @DeleteMapping("/delete/{productId}/{imageName}")
    public ResponseEntity<Boolean> deleteImageByProductIdAndName(@PathVariable String productId, @PathVariable String imageName) {
        var imageDeleted = productImageService.deleteImageFile(productId, imageName);
        if (imageDeleted) {
            return new ResponseEntity<>(imageDeleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(imageDeleted, HttpStatus.BAD_REQUEST);
        }
    }

}
