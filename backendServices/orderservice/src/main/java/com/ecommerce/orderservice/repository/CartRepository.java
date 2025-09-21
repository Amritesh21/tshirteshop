package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.entity.CartEntity;
import com.ecommerce.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    List<CartEntity> getByBuyerUsername(String username);

}
