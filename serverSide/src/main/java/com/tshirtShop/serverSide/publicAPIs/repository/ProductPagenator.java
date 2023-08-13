package com.tshirtShop.serverSide.publicAPIs.repository;

import com.tshirtShop.serverSide.publicAPIs.entity.ProductList;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPagenator extends PagingAndSortingRepository<ProductList, Long> {
}
