package com.ecommerce.orderservice.utils.communicator;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventoryservice")
public interface InventoryCommunicator {

    @GetMapping("/api/buyer/purchase")
    ResponseEntity<Boolean> updateStockOnPurchaseOrCancel(@RequestHeader("Bearer-Token") String token, @RequestParam String productId, @RequestParam Long quantity);

}
