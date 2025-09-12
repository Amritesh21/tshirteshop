package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.dto.PublishProductDTO;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.repository.ProductDetailsRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductPublishConsumerImpl {

    private final ProductDetailsRepository productDetailsRepository;

    public ProductPublishConsumerImpl(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }

    @KafkaListener(topics = "publishProduct_3", groupId = "inventoryManager_2")
    public void consumePublishProductMessage(PublishProductDTO publishProductDTO) {
       Product product = productDetailsRepository.findByProductId(publishProductDTO.productId()).orElseThrow();
       product.setPublished(true);
       productDetailsRepository.save(product);
    }

}
