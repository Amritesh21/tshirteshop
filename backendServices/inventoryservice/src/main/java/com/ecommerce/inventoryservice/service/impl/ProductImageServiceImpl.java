package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.dto.ProductImageDTO;
import com.ecommerce.inventoryservice.service.ProductImageService;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ProductImageServiceImpl implements ProductImageService {


    @Override
    public boolean addImageForAProduct(ProductImageDTO productImageDTO) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream(productImageDTO.getImageName())
            );
            bufferedOutputStream.write(productImageDTO.getImageFile().getBytes());
        } catch (IOException e) {
            return false;
            // throw new RuntimeException(e);
        }
        return true;
    }
}
