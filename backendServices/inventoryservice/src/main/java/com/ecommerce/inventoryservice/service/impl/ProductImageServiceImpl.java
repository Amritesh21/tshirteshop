package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.constants.ExceptionMessages;
import com.ecommerce.inventoryservice.dto.productImage.ProductImageDTO;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.exceptions.ErrorOccurredWhileSavingImageFile;
import com.ecommerce.inventoryservice.exceptions.ImageFileAlreadyExistsException;
import com.ecommerce.inventoryservice.exceptions.ImageFileNotFoundException;
import com.ecommerce.inventoryservice.exceptions.InvalidProductIdException;
import com.ecommerce.inventoryservice.repository.ProductDetailsRepository;
import com.ecommerce.inventoryservice.service.ProductImageService;
import com.ecommerce.inventoryservice.utils.ProductImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductDetailsRepository productDetailsRepository;

    private ProductImageUtils productImageUtils;

    @Autowired
    public ProductImageServiceImpl(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }

    @Autowired
    public void setProductImageUtils(ProductImageUtils productImageUtils) {
        this.productImageUtils = productImageUtils;
    }


    @Override
    public boolean addImageForAProduct(ProductImageDTO productImageDTO) {
        try {
            Optional<Product> product = productDetailsRepository.findById(productImageDTO.getProductId());
            if (product.isEmpty()) {
                throw new InvalidProductIdException(ExceptionMessages.InvalidProductIdEntered.getMessage());
            }

            boolean isImageNotSaved = productImageUtils.isImageAlreadyPresentForTheProduct(productImageDTO.getProductId(), productImageDTO.getImageName());
            boolean isImageNotAddedInDB = product.get().getImage().stream().filter(x -> x.equals(productImageDTO.getImageName())).findAny().isEmpty();
            if (!isImageNotSaved && !isImageNotAddedInDB) {
                throw new ImageFileAlreadyExistsException(ExceptionMessages.ImageFileAlreadyExistsException.getMessage());
            }

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream("productImages/"+productImageDTO.getProductId()+"/"+productImageDTO.getImageName())
            );
            bufferedOutputStream.write(productImageDTO.getImageFile().getBytes());

            product.get().getImage().add(productImageDTO.getImageName());
            productDetailsRepository.save(product.get());

        } catch (IOException e) {
            throw new ErrorOccurredWhileSavingImageFile(ExceptionMessages.SaveImageFileException.getMessage());
        }
        return true;
    }

    @Override
    public byte[] getImageByProductIdAndImageName(String productId, String imageName) {
        boolean isImageNotSaved = productImageUtils.isImageAlreadyPresentForTheProduct(productId, imageName);
        if (isImageNotSaved) {
            throw new ImageFileNotFoundException(ExceptionMessages.ImageFileNotFoundException.getMessage());
        }
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("productImages/"+productId+"/"+imageName));
            return bufferedInputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(ExceptionMessages.ErrorWhileReadingImageFile.getMessage());
        }
    }

    @Override
    public String getContentType(String productId, String imageName) {
        Path imagePath = Path.of("productImages", productId, imageName);
        try {
            return Files.probeContentType(imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteImageFile(String productId, String imageName) {
        boolean isImageNotSaved = productImageUtils.isImageAlreadyPresentForTheProduct(productId, imageName);
        if (isImageNotSaved) {
            throw new ImageFileNotFoundException(ExceptionMessages.ImageFileNotFoundException.getMessage());
        }
        File file = new File("productImages/" + productId + "/"+ imageName);
        return file.delete();
    }
}
