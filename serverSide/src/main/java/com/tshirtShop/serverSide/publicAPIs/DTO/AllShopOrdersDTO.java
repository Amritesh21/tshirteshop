package com.tshirtShop.serverSide.publicAPIs.DTO;

import java.util.Date;

public class AllShopOrdersDTO {

    Long orderId;
    Long totalCost;
    String orderStatus;
    Date orderDate;
    String productCategory;

    public AllShopOrdersDTO() {
    }

    public AllShopOrdersDTO(Long orderId, Long totalCost, String orderStatus, Date orderDate, String productCategory) {
        this.orderId = orderId;
        this.totalCost = totalCost;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.productCategory = productCategory;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
