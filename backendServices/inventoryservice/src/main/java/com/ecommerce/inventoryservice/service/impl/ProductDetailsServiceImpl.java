package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.constants.ExceptionMessages;
import com.ecommerce.inventoryservice.dto.ProductDetailsDTO;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.exceptions.ErrorOccurredWhileCreatingDirectory;
import com.ecommerce.inventoryservice.exceptions.ProductAlreadyExistsException;
import com.ecommerce.inventoryservice.repository.ProductDetailsRepository;
import com.ecommerce.inventoryservice.service.ProductDetailsService;
import com.ecommerce.inventoryservice.utils.ProductDetailsMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;
    private final ProductDetailsMapper productDetailsMapper;

    @Autowired
    ProductDetailsServiceImpl(ProductDetailsRepository productDetailsRepository,
                              ProductDetailsMapper productDetailsMapper) {
        this.productDetailsRepository = productDetailsRepository;
        this.productDetailsMapper = productDetailsMapper;
    }

    @Override
    @Transactional
    public String createProduct(ProductDetailsDTO productDetailsDTO) {
        Optional<Product> existingProduct = productDetailsRepository.findByProductName(productDetailsDTO.getProductName());
        if (existingProduct.isPresent()) {
            throw new ProductAlreadyExistsException(ExceptionMessages.ProductAlreadyExists.getMessage());
        }
        Product product = productDetailsMapper.createNewProductMapper(productDetailsDTO);
        productDetailsRepository.save(product);
        try {
            Files.createDirectories(Paths.get("productImages", product.getProductId()));
        } catch (IOException e) {
            throw new ErrorOccurredWhileCreatingDirectory(ExceptionMessages.CreateDirectoryException.getMessage());
        }
        return product.getProductId();
    }
}
