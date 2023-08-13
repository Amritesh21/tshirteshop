package com.tshirtShop.serverSide.publicAPIs.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Cacheable(false)
public class CustomerOrderCart {

    @Id
    @GeneratedValue
    private Long id;

    private Long productId;

    private String customerName;

    private String color;

    private String size;

    private Long quantity;

    public CustomerOrderCart(Long productId, String customerName, String color, String size, Long quantity) {
        this.productId = productId;
        this.customerName = customerName;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    public CustomerOrderCart() {

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
