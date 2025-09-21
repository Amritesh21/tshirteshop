package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buyer")
public class PurchaseController {

    PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchase")
    public ResponseEntity<Boolean> updateStockOnPurchaseOrCancel(@RequestParam String productId, @RequestParam Long quantity) {
        var isStockUpdated = purchaseService.productPurchasedOrReturned(productId, quantity);
        if (isStockUpdated) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.ok().body(false);
        }
    }

}
