package com.ecommerce.inventoryservice.repository;

import com.ecommerce.inventoryservice.entity.ProductImage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductImageRepository extends MongoRepository<ProductImage, ObjectId> {
}
