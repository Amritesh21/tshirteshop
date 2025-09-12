package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.PublishProductDTO;

public interface ProductPublishConsumer {

    public void consumePublishMessage(PublishProductDTO publishProductDTO);

}
