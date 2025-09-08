package com.ecommerce.inventoryservice.utils;

import com.ecommerce.inventoryservice.dto.ProductDetailsDTO;
import com.ecommerce.inventoryservice.entity.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@Scope("prototype")
public class ProductDetailsMapper {

    public Product mapProductDTOtoEntity(ProductDetailsDTO productDetailsDTO, Product product) {
        product.setProductName(productDetailsDTO.getProductName());
        product.setCategory(productDetailsDTO.getCategory());
        product.setColors(productDetailsDTO.getColors());
        product.setDescription(productDetailsDTO.getDescription());
        product.setPrice(productDetailsDTO.getPrice());
        product.setStock(productDetailsDTO.getStock());
        return product;
    }

    public Product createNewProductMapper(ProductDetailsDTO productDetailsDTO) {
        Product product = new Product();
        product.setCreatedOn(LocalDateTime.now());
        product.setImage(new ArrayList<>());
        product.setThumbnail("");
        return mapProductDTOtoEntity(productDetailsDTO, product);
    }

}
