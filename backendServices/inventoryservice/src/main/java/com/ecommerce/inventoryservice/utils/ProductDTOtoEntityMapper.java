package com.ecommerce.inventoryservice.utils;

import com.ecommerce.inventoryservice.constants.Category;
import com.ecommerce.inventoryservice.dto.productMetaData.ClothProductMetaDataDTO;
import com.ecommerce.inventoryservice.dto.productMetaData.CompleteProductMetaDataDTO;
import com.ecommerce.inventoryservice.dto.productMetaData.CosmeticProductMetaDataDTO;
import com.ecommerce.inventoryservice.dto.productMetaData.ProductMetaDataDTO;
import com.ecommerce.inventoryservice.entity.ClothProduct;
import com.ecommerce.inventoryservice.entity.CosmeticProduct;
import com.ecommerce.inventoryservice.entity.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@Scope("prototype")
public class ProductDTOtoEntityMapper {

    public Product mapCoreProductMeta(ProductMetaDataDTO productMetaDataDTO, Product product) {
        product.setProductName(productMetaDataDTO.getProductName());
        product.setCategory(productMetaDataDTO.getCategory());
        product.setDescription(productMetaDataDTO.getDescription());
        return product;
    }

    public void mapCompleteProductMeta(CompleteProductMetaDataDTO completeProductMetaDataDTO, Product product) {
        product.setStock(completeProductMetaDataDTO.getStock());
        product.setPrice(completeProductMetaDataDTO.getPrice());
        product.setPublished(completeProductMetaDataDTO.isPublish());
        product.setMaxPurchaseLimit(completeProductMetaDataDTO.getMaxPurchaseLimit());
    }

    public void mapClothProductMeta(ClothProductMetaDataDTO clothProductMetaDataDTO, ClothProduct product) {
        product.setBrands(clothProductMetaDataDTO.getBrands());
        product.setSizes(clothProductMetaDataDTO.getSizes());
        product.setColors(clothProductMetaDataDTO.getColors());
    }

    public void mapCosmeticProductMeta(CosmeticProductMetaDataDTO cosmeticProductMetaDataDTO, CosmeticProduct product) {
        product.setBrand(cosmeticProductMetaDataDTO.getBrand());
        product.setManufacturedOn(cosmeticProductMetaDataDTO.getManufacturedOn());
        product.setUseBy(cosmeticProductMetaDataDTO.getUseBy());
    }

    public Product createNewProductMapper(ProductMetaDataDTO productMetaDataDTO) {
        Product product = switch (productMetaDataDTO.getCategory()) {
            case Category.CLOTH -> new ClothProduct();
            case Category.COSMETIC -> new CosmeticProduct();
            default -> new Product();
        };
        product.setCreatedOn(LocalDateTime.now());
        product.setImage(new ArrayList<>());
        product.setThumbnail("");
        return mapCoreProductMeta(productMetaDataDTO, product);
    }

}
