package com.ecommerce.authservice.repository;

import com.ecommerce.authservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EStoreUserDetailsRepo extends JpaRepository<UserEntity, String> {
}
