package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.PublishProductDTO;

public interface ProductPublishProducer {

    void sendPublishMessage(PublishProductDTO publishProductDTO);

}
