package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByBuyerUsername(String username);
}
