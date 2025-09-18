package com.ecommerce.authservice.repository;

import com.ecommerce.authservice.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EStoreUserAuthorityRepo extends JpaRepository<Authority, String> {
}
