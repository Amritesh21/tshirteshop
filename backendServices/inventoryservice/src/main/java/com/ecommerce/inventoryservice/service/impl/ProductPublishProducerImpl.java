package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.dto.PublishProductDTO;
import com.ecommerce.inventoryservice.service.ProductPublishProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProductPublishProducerImpl implements ProductPublishProducer {

    KafkaTemplate<String, PublishProductDTO> kafkaPublisherTemplate;

    public ProductPublishProducerImpl(KafkaTemplate<String, PublishProductDTO> kafkaPublisherTemplate) {
        this.kafkaPublisherTemplate = kafkaPublisherTemplate;
    }

    @Override
    public void sendPublishMessage(PublishProductDTO publishProductDTO) {
        Message<PublishProductDTO> message = MessageBuilder
                .withPayload(publishProductDTO)
                .setHeader(KafkaHeaders.TOPIC, "publishProduct_3")
                .build();
        kafkaPublisherTemplate.send(message);
    }
}
