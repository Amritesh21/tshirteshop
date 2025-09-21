package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.repository.ProductDetailsRepository;
import com.ecommerce.inventoryservice.service.PurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final ProductDetailsRepository productDetailsRepository;

    PurchaseServiceImpl(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }

    @Transactional
    @Override
    public boolean productPurchasedOrReturned(String productId, Long quantity) {
        Optional<Product> productOptional = productDetailsRepository.findById(productId);
        if (productOptional.isEmpty()) {
            return false;
        }
        Product product = productOptional.get();
        if (product.getStock() < quantity && quantity > 0) {
            return false;
        } else {
            product.setStock(product.getStock() - quantity);
            productDetailsRepository.save(product);
            return true;
        }
    }
}
