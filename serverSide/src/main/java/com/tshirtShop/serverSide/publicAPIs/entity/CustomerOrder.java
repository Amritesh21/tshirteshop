package com.tshirtShop.serverSide.publicAPIs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue
    Long orderId;

    String buyer;

    @ManyToOne
    ProductList productList;

    String size;

    Long quantity;

    String color;

    String paymentMethod;

    Long totalCost;

    String address;

    Long phno;

    public CustomerOrder() {
    }

    public CustomerOrder(String buyer, ProductList productList, String size, Long quantity, String paymentMethod, String color, Long totalCost) {
        this.buyer = buyer;
        this.productList = productList;
        this.size = size;
        this.quantity = quantity;
        this.paymentMethod = paymentMethod;
        this.color = color;
        this.totalCost = totalCost;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhno() {
        return phno;
    }

    public void setPhno(Long phno) {
        this.phno = phno;
    }
}
