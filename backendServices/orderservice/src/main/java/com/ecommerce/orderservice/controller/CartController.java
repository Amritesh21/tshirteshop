package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.CartDTO;
import com.ecommerce.orderservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyer/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addItemToCart(@RequestBody CartDTO cartDTO) {
        var isItemAddedToCart = cartService.addToCart(cartDTO);
        if (isItemAddedToCart) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Boolean> removeItemFromCart(@PathVariable Long cartItemId) {
        var isItemRemovedFromCart = cartService.removeFromCart(cartItemId);
        if (isItemRemovedFromCart) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartDTO>> getAllItemsInCart() {
        List<CartDTO> getAllItems = cartService.getAllProductsInCart();
        return ResponseEntity.ok().body(getAllItems);
    }

}
