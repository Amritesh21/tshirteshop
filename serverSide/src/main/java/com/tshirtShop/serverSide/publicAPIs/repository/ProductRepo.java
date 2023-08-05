package com.tshirtShop.serverSide.publicAPIs.repository;

import com.tshirtShop.serverSide.publicAPIs.entity.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductList, Long> {
}
