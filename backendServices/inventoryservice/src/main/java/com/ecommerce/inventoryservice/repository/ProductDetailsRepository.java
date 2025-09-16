package com.ecommerce.inventoryservice.repository;

import com.ecommerce.inventoryservice.entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductDetailsRepository extends MongoRepository<Product, String> {

    Optional<Product> findByProductName(String name);
}
