package com.ecommerce.inventoryservice.utils;

import com.ecommerce.inventoryservice.constants.Category;
import com.ecommerce.inventoryservice.dto.productMetaData.ClothProductMetaDataDTO;
import com.ecommerce.inventoryservice.dto.productMetaData.CompleteProductMetaDataDTO;
import com.ecommerce.inventoryservice.dto.productMetaData.CosmeticProductMetaDataDTO;
import com.ecommerce.inventoryservice.dto.productMetaData.ProductMetaDataDTO;
import com.ecommerce.inventoryservice.entity.ClothProduct;
import com.ecommerce.inventoryservice.entity.CosmeticProduct;
import com.ecommerce.inventoryservice.entity.Product;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class ProductEntityToDTOMapper {

    public ProductMetaDataDTO mapCoreProductMeta(ProductMetaDataDTO productMetaDataDTO, Product product) {
        productMetaDataDTO.setProductName(product.getProductName());
        productMetaDataDTO.setCategory(product.getCategory());
        productMetaDataDTO.setDescription(product.getDescription());
        return productMetaDataDTO;
    }

    public void mapCompleteProductMeta(CompleteProductMetaDataDTO completeProductMetaDataDTO, Product product) {
        completeProductMetaDataDTO.setStock(product.getStock());
        completeProductMetaDataDTO.setPrice(product.getPrice());
        completeProductMetaDataDTO.setPublish(product.isPublished());
        completeProductMetaDataDTO.setMaxPurchaseLimit(product.getMaxPurchaseLimit());
    }

    public void mapClothProductMeta(ClothProductMetaDataDTO clothProductMetaDataDTO, ClothProduct product) {
        clothProductMetaDataDTO.setBrands(product.getBrands());
        clothProductMetaDataDTO.setSizes(product.getSizes());
        clothProductMetaDataDTO.setColors(product.getColors());
    }

    public void mapCosmeticProductMeta(CosmeticProductMetaDataDTO cosmeticProductMetaDataDTO, CosmeticProduct product) {
        cosmeticProductMetaDataDTO.setBrand(product.getBrand());
        cosmeticProductMetaDataDTO.setManufacturedOn(product.getManufacturedOn());
        cosmeticProductMetaDataDTO.setUseBy(product.getUseBy());
    }

    public ProductMetaDataDTO createNewProductMapper(Product product) {
        ProductMetaDataDTO productMetaDataDTO = switch (product.getCategory()) {
            case Category.CLOTH -> new ClothProductMetaDataDTO();
            case Category.COSMETIC -> new CosmeticProductMetaDataDTO();
            default -> new ProductMetaDataDTO();
        };
        product.setCreatedOn(LocalDateTime.now());
        product.setImage(new ArrayList<>());
        product.setThumbnail("");
        return mapCoreProductMeta(productMetaDataDTO, product);
    }

}
