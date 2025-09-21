package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.constants.ExceptionMessage;
import com.ecommerce.orderservice.dto.CartDTO;
import com.ecommerce.orderservice.entity.CartEntity;
import com.ecommerce.orderservice.repository.CartRepository;
import com.ecommerce.orderservice.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    @Override
    public boolean addToCart(CartDTO cartDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        CartEntity cartEntity = new CartEntity();
        cartEntity.setBuyerUsername(username);
        cartEntity.setProductId(cartDTO.getProductId());
        cartEntity.setQuantity(cartDTO.getQuantity());
        cartRepository.save(cartEntity);
        return true;
    }

    @Transactional
    @Override
    public boolean removeFromCart(Long id) {
        Optional<CartEntity> cartEntityOptional = cartRepository.findById(id);
        if (cartEntityOptional.isEmpty()) {
            throw new RuntimeException(ExceptionMessage.INVALID_CART_ACCESS.getMessage());
        }
        CartEntity cartEntity = cartEntityOptional.get();
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!cartEntity.getBuyerUsername().equals(loggedInUsername)) {
            throw new RuntimeException(ExceptionMessage.UNAUTHORIZED_ATTEMPT.getMessage());
        }
        cartRepository.delete(cartEntity);
        return true;
    }

    @Transactional
    @Override
    public List<CartDTO> getAllProductsInCart() {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        List<CartEntity> cartEntities = cartRepository.getByBuyerUsername(loggedInUsername);
        List<CartDTO> cartDTOS = cartEntities.stream().map(x -> {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setProductId(x.getProductId());
            cartDTO.setQuantity(x.getQuantity());
            return cartDTO;
        }).toList();
        return cartDTOS;
    }
}
