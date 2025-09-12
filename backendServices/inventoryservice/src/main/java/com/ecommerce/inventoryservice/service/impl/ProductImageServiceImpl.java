package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.constants.ExceptionMessages;
import com.ecommerce.inventoryservice.dto.ProductImageDTO;
import com.ecommerce.inventoryservice.dto.PublishProductDTO;
import com.ecommerce.inventoryservice.exceptions.ErrorOccurredWhileSavingImageFile;
import com.ecommerce.inventoryservice.service.ProductImageService;
import com.ecommerce.inventoryservice.service.ProductPublishProducer;
import com.ecommerce.inventoryservice.utils.ProductImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    Logger logger = LoggerFactory.getLogger(ProductImageServiceImpl.class);

    private ProductImageUtil productImageUtil;
    private ProductPublishProducer productPublishProducer;

    @Autowired
    public void setProductImageUtil(ProductImageUtil productImageUtil) {
        this.productImageUtil = productImageUtil;
    }

    @Autowired
    public void setProductPublishProducer(ProductPublishProducer productPublishProducer) {
        this.productPublishProducer = productPublishProducer;
    }

    @Override
    public boolean addImageForAProduct(ProductImageDTO productImageDTO, int totalImagesCount) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream("productImages/"+productImageDTO.getProductId()+"/"+productImageDTO.getImageName())
            );
            bufferedOutputStream.write(productImageDTO.getImageFile().getBytes());
            boolean allImagesUploaded = false; // productImageUtil.checkIfAllImagesAreUploaded(productImageDTO, totalImagesCount);
            if (allImagesUploaded) {
               productPublishProducer.sendPublishMessage(new PublishProductDTO(true, productImageDTO.getProductId()));
            }
            return true;
        } catch (IOException e) {
            logger.info(e.toString());
            throw new ErrorOccurredWhileSavingImageFile(ExceptionMessages.SaveImageFileException.getMessage());
        }
    }
}
