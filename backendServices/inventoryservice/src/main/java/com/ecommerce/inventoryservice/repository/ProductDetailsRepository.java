package com.ecommerce.inventoryservice.repository;

import com.ecommerce.inventoryservice.entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductDetailsRepository extends MongoRepository<Product, ObjectId> {
}
