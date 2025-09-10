package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.constants.ExceptionMessages;
import com.ecommerce.inventoryservice.dto.ProductImageDTO;
import com.ecommerce.inventoryservice.exceptions.ErrorOccurredWhileSavingImageFile;
import com.ecommerce.inventoryservice.service.ProductImageService;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ProductImageServiceImpl implements ProductImageService {


    @Override
    public boolean addImageForAProduct(ProductImageDTO productImageDTO) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream("productImages/"+productImageDTO.getProductId()+"/"+productImageDTO.getImageName())
            );
            bufferedOutputStream.write(productImageDTO.getImageFile().getBytes());
        } catch (IOException e) {
            throw new ErrorOccurredWhileSavingImageFile(ExceptionMessages.SaveImageFileException.getMessage());
        }
        return true;
    }
}
