package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.constants.Category;
import com.ecommerce.inventoryservice.constants.ExceptionMessages;
import com.ecommerce.inventoryservice.dto.productMetaData.*;
import com.ecommerce.inventoryservice.entity.ClothProduct;
import com.ecommerce.inventoryservice.entity.CosmeticProduct;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.exceptions.ErrorOccurredWhileCreatingDirectory;
import com.ecommerce.inventoryservice.exceptions.InvalidProductIdException;
import com.ecommerce.inventoryservice.exceptions.ProductAlreadyExistsException;
import com.ecommerce.inventoryservice.repository.ProductDetailsRepository;
import com.ecommerce.inventoryservice.service.ProductDetailsService;
import com.ecommerce.inventoryservice.utils.ProductDTOtoEntityMapper;
import com.ecommerce.inventoryservice.utils.ProductEntityToDTOMapper;
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
    private final ProductDTOtoEntityMapper productDTOtoEntityMapper;
    private final ProductEntityToDTOMapper productEntityToDTOMapper;

    @Autowired
    ProductDetailsServiceImpl(ProductDetailsRepository productDetailsRepository,
                              ProductDTOtoEntityMapper productDTOtoEntityMapper,
                              ProductEntityToDTOMapper productEntityToDTOMapper
    ) {
        this.productDetailsRepository = productDetailsRepository;
        this.productDTOtoEntityMapper = productDTOtoEntityMapper;
        this.productEntityToDTOMapper = productEntityToDTOMapper;
    }

    @Override
    @Transactional
    public String createProduct(ProductMetaDataDTO productMetaDataDTO) {
        Optional<Product> existingProduct = productDetailsRepository.findByProductName(productMetaDataDTO.getProductName());
        if (existingProduct.isPresent()) {
            throw new ProductAlreadyExistsException(ExceptionMessages.ProductAlreadyExists.getMessage());
        }
        Product product = productDTOtoEntityMapper.createNewProductMapper(productMetaDataDTO);
        productDetailsRepository.save(product);
        try {
            Files.createDirectories(Paths.get("productImages", product.getProductId()));
        } catch (IOException e) {
            throw new ErrorOccurredWhileCreatingDirectory(ExceptionMessages.CreateDirectoryException.getMessage());
        }
        return product.getProductId();
    }

    @Override
    @Transactional
    public ProductUpdatedAndPublishedDTO updateProduct(CompleteProductMetaDataDTO completeProductMetaDataDTO) {
        Optional<Product> productOptional = productDetailsRepository.findById(completeProductMetaDataDTO.getProductId());
        if (productOptional.isEmpty()) {
            throw new ProductAlreadyExistsException(ExceptionMessages.InvalidProductIdEntered.getMessage());
        }
        Product product = productOptional.get();
        productDTOtoEntityMapper.mapCoreProductMeta(completeProductMetaDataDTO, product);
        productDTOtoEntityMapper.mapCompleteProductMeta(completeProductMetaDataDTO, product);
        switch (product.getCategory()) {
            case Category.CLOTH -> productDTOtoEntityMapper.mapClothProductMeta((ClothProductMetaDataDTO) completeProductMetaDataDTO, (ClothProduct) product);
            case Category.COSMETIC -> productDTOtoEntityMapper.mapCosmeticProductMeta((CosmeticProductMetaDataDTO) completeProductMetaDataDTO, (CosmeticProduct) product);
        }
        productDetailsRepository.save(product);
        return new ProductUpdatedAndPublishedDTO(true, product.isPublished());
    }

    @Override
    public CompleteProductMetaDataDTO getProductDetails(String productId) {
        Optional<Product> optionalProduct = productDetailsRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new InvalidProductIdException(ExceptionMessages.InvalidProductIdEntered.getMessage());
        }
        Product product = optionalProduct.get();
        CompleteProductMetaDataDTO completeProductMetaDataDTO = (CompleteProductMetaDataDTO) productEntityToDTOMapper.createNewProductMapper(product);
        productEntityToDTOMapper.mapCompleteProductMeta(completeProductMetaDataDTO, product);
        switch (completeProductMetaDataDTO.getCategory()) {
            case Category.CLOTH -> productEntityToDTOMapper.mapClothProductMeta((ClothProductMetaDataDTO) completeProductMetaDataDTO, (ClothProduct) product);
            case Category.COSMETIC -> productEntityToDTOMapper.mapCosmeticProductMeta((CosmeticProductMetaDataDTO) completeProductMetaDataDTO, (CosmeticProduct) product);
        }
        return completeProductMetaDataDTO;
    }

}
