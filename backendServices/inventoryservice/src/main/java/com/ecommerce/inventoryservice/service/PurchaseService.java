package com.ecommerce.inventoryservice.service;

public interface PurchaseService {

    boolean productPurchasedOrReturned(String productId, Long quantity);

}
