package com.ecommerce.authservice.repository;

import com.ecommerce.authservice.entity.LoginTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EStoreLoginTokenRepo extends JpaRepository<LoginTokenEntity, Long> {

    List<LoginTokenEntity> findByUserUsername(String username);

    void deleteByToken(String token);

}
