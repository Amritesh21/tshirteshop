package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.CartDTO;

import java.util.List;

public interface CartService {

    boolean addToCart(CartDTO cartDTO);

    boolean removeFromCart(Long id);

    List<CartDTO> getAllProductsInCart();

}
