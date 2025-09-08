package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.dto.ProductDetailsDTO;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.repository.ProductDetailsRepository;
import com.ecommerce.inventoryservice.service.ProductDetailsService;
import com.ecommerce.inventoryservice.utils.ProductDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;
    private final ProductDetailsMapper productDetailsMapper;

    @Autowired
    ProductDetailsServiceImpl(ProductDetailsRepository productDetailsRepository,
                              ProductDetailsMapper productDetailsMapper) {
        this.productDetailsRepository = productDetailsRepository;
        this.productDetailsMapper = productDetailsMapper;
    }

    @Override
    @Transactional
    public boolean createProduct(ProductDetailsDTO productDetailsDTO) {
        Product product = productDetailsMapper.createNewProductMapper(productDetailsDTO);
        productDetailsRepository.save(product);
        return true;
    }
}
